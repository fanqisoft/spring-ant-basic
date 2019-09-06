package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmAuthority;
import cn.coreqi.server.entity.PmAuthorityExample;
import cn.coreqi.server.entity.PmRoleAndAuthority;
import cn.coreqi.server.entity.PmRoleAndAuthorityExample;
import cn.coreqi.server.enums.AuthorityTypeEnum;
import cn.coreqi.server.model.ControllerMethodModel;
import cn.coreqi.server.model.PmAuthorityModel;
import cn.coreqi.server.repository.entityMapper.PmAuthorityMapper;
import cn.coreqi.server.repository.entityMapper.PmRoleAndAuthorityMapper;
import cn.coreqi.server.repository.modelMapper.AuthorityModelMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.google.common.collect.Ordering;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmAuthorityMapper authorityMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AuthorityModelMapper authorityModelMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmRoleAndAuthorityMapper roleAndAuthorityMapper;

    @Autowired
    private AdminService adminService;


    @Override
    public List<PmAuthority> getList() {
        return authorityMapper.selectByExample(null);
    }

    @Override
    public PageInfo<PmAuthority> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        return new PageInfo<>(authorityMapper.selectByExample(null));
    }

    @Override
    public List<PmAuthorityModel> getAll() {
        return authorityModelMapper.selectAll();
    }

    @Override
    public List<PmAuthority> getTreeList() {
        return getTreeList("");
    }

    @Override
    public List<PmAuthority> getTreeList(String id) {
        var list = authorityMapper.selectByExample(null);
        var resultList = genTreeById(list, id);
        return resultList;
    }

    @Override
    public PmAuthority getById(String id) {
        return authorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public PmAuthority create(PmAuthority entity) throws ProjectException {
        entity.setId(UUID.randomUUID().toString());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setIsDel(entity.getIsDel() == null ? false : entity.getIsDel());
        entity.setVirtualUrl(entity.getVirtualUrl());
        entity.setType(entity.getType());

        int affectRow = authorityMapper.insert(entity);
        if (affectRow == 0) {
            throw new ProjectException("创建失败");
        }

        reGenerateOrderAndFullPath();

        return authorityMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    public PmAuthority update(PmAuthority entity) throws ProjectException {
        PmAuthority updateEntity = authorityMapper.selectByPrimaryKey(entity.getId());

        PmAuthority oldParentEntity = authorityMapper.selectByPrimaryKey(updateEntity.getParentId());
        var ischild = isChild(entity.getId(), entity.getParentId());
        if (ischild) {
            var childrenList = getDirectChildren(updateEntity.getId());
            for (var childData : childrenList) {
                childData.setParentId(oldParentEntity.getId());
                authorityMapper.updateByPrimaryKey(childData);
            }
        }

        updateEntity.setName(entity.getName());
        updateEntity.setFullPath(entity.getFullPath());
        updateEntity.setParentId(entity.getParentId());
        updateEntity.setDescription(entity.getDescription());
        updateEntity.setSummary(entity.getSummary());
        updateEntity.setVirtualUrl(entity.getVirtualUrl());
        updateEntity.setType(entity.getType());
        updateEntity.setOrderCode(entity.getOrderCode());
        updateEntity.setUpdateTime(LocalDateTime.now());

        int affectRow = authorityMapper.updateByPrimaryKey(updateEntity);
        if (affectRow == 0) {
            throw new ProjectException("更新失败");
        }

        reGenerateOrderAndFullPath();

        return authorityMapper.selectByPrimaryKey(updateEntity.getId());
    }

    @Override
    public PmAuthority delete(String id) throws ProjectException {
        return delete(id, false);
    }

    @Override
    public PmAuthority delete(String id, Boolean isTreeDelete) throws ProjectException {
        PmAuthority deleteData = getById(id);
        if (deleteData == null) {
            throw new ProjectException("数据不存在,请刷新重试");
        }

        List<PmAuthority> childrenList = getDirectChildren(id);

        if (isTreeDelete) {
            deleteByParentId(deleteData.getId());
        } else {
            //更新子节点挂载
            for (var childData : childrenList) {
                childData.setParentId(deleteData.getParentId());
                authorityMapper.updateByPrimaryKey(childData);
            }
        }

        int affectRow = simpleDelete(id);
        if (affectRow == 0) {
            throw new ProjectException("删除失败");
        }

        reGenerateOrderAndFullPath();
        return deleteData;
    }

    @Override
    public List<PmAuthority> getListByRoleId(String roleId) {

        PmRoleAndAuthorityExample roleAndAuthExample = new PmRoleAndAuthorityExample();
        PmRoleAndAuthorityExample.Criteria roleAndAuthCriteria = roleAndAuthExample.createCriteria();
        roleAndAuthCriteria.andRoleIdEqualTo(roleId);

        List<PmRoleAndAuthority> raaList = roleAndAuthorityMapper.selectByExample(roleAndAuthExample);

        if (raaList.isEmpty()) {
            return new ArrayList<>();
        }
        PmAuthorityExample authExample = new PmAuthorityExample();
        PmAuthorityExample.Criteria authCriteria = authExample.createCriteria();

        authCriteria.andIdIn(raaList.stream().map(PmRoleAndAuthority::getAuthorityId).collect(Collectors.toList()));

        return authorityMapper.selectByExample(authExample);
    }

    @Override
    public void generateServerAuthority() {
        //新控制器权限
        List<PmAuthority> newControllerAuthList = new ArrayList<>();
        //新方法权限
        List<PmAuthority> newMethodAuthList = new ArrayList<>();
        //数据权限
        List<PmAuthority> newDataSecurityAuthList = new ArrayList<>();
        //新权限
        List<PmAuthority> newAuthList = new ArrayList<>();
        //原权限
        List<PmAuthority> oldServerAuthList;

        PmAuthorityExample example = new PmAuthorityExample();
        example.createCriteria()
                .andFullPathEqualTo(AuthorityTypeEnum.Server.getRootName());
        //服务器根权限
        PmAuthority serverRootAuth = authorityMapper.selectByExample(example)
                .stream().findFirst().get();

        List<ControllerMethodModel> controllerMethodList = adminService.getControllerMethodList();


        //组织新权限
        for (ControllerMethodModel method : controllerMethodList) {
            PmAuthority newAuth = new PmAuthority();
            newAuth.setId(UUID.randomUUID().toString());
            newAuth.setCreateTime(LocalDateTime.now());
            newAuth.setUpdateTime(LocalDateTime.now());
            newAuth.setName(method.getName());
            newAuth.setVirtualUrl(method.getMapUrl());
            newAuth.setType(AuthorityTypeEnum.Server.getId());
            newAuth.setIsDel(false);
            newAuth.setFullPath(AuthorityTypeEnum.Server.getRootName() + "." + method.getFullPath());
            switch (method.getType()) {
                case "controller":
                    newAuth.setParentId(serverRootAuth.getId());
                    newControllerAuthList.add(newAuth);
                    break;
                case "method":
                    newMethodAuthList.add(newAuth);
                    break;
                case "dataSecurity":
                    newDataSecurityAuthList.add(newAuth);
                    break;
            }

            newAuthList.add(newAuth);
        }

        //获取旧权限
        example.clear();
        example
                .createCriteria()
                .andFullPathLike(AuthorityTypeEnum.Server.getRootName() + "." + "%");
        oldServerAuthList = authorityMapper.selectByExample(example);


        //拷贝相同权限ID
        List<PmAuthority> finalOldServerAuthList = oldServerAuthList;
        for (PmAuthority newAuth : newAuthList) {
            Optional<PmAuthority> oldAuth = finalOldServerAuthList
                    .stream()
                    .filter(j -> j.getFullPath().equals(newAuth.getFullPath()))
                    .findFirst();

            oldAuth.ifPresent(pmAuthority -> newAuth.setId(pmAuthority.getId()));
        }


        //删除引用
        List<PmAuthority> toDelAuthRef =
                oldServerAuthList.stream()
                        .filter(i ->
                                newAuthList.stream().noneMatch(j -> j.getId().equals(i.getId()))
                        ).collect(Collectors.toList());
        if (toDelAuthRef.size() != 0) {
            PmRoleAndAuthorityExample example2 = new PmRoleAndAuthorityExample();
            example2.createCriteria().andAuthorityIdIn(
                    toDelAuthRef.stream().map(PmAuthority::getId).collect(Collectors.toList())
            );
            roleAndAuthorityMapper.deleteByExample(example2);
        }

        //删除旧权限
        authorityMapper.deleteByExample(example);

        //赋予子权限父ID
        for (PmAuthority auth : newMethodAuthList) {
            Optional<PmAuthority> parentAuth = newControllerAuthList
                    .stream()
                    .filter(i -> auth.getFullPath().startsWith(i.getFullPath()))
                    .findFirst();

            parentAuth.ifPresent(i -> auth.setParentId(i.getId()));
        }
        for (PmAuthority auth : newDataSecurityAuthList) {
            Optional<PmAuthority> parentAuth = newMethodAuthList
                    .stream()
                    .filter(i -> auth.getFullPath().startsWith(i.getFullPath()))
                    .findFirst();

            parentAuth.ifPresent(i -> auth.setParentId(i.getId()));
        }

        //存入权限
        for (PmAuthority newAuth : newAuthList) {
            authorityMapper.insert(newAuth);
        }

        reGenerateOrderAndFullPath();
    }


    //region 工具方法

    /**
     * 重新生成全路径与排序
     */
    private void reGenerateOrderAndFullPath() {
        List<PmAuthority> itemList = authorityMapper.selectByExample(null);
        String rootParentId = "";
        String rootOrderStr = "";
        String rootFullPath = "";

        reGenerateOrderAndFullPath(rootParentId, rootOrderStr, rootFullPath, itemList);

        for (PmAuthority item : itemList) {
            authorityMapper.updateByPrimaryKey(item);
        }
    }


    /**
     * 重新生成全路径与排序
     *
     * @param parentId
     * @param parentOrderStr
     * @param parentFullPath
     * @param itemList
     */
    private void reGenerateOrderAndFullPath(String parentId,
                                            String parentOrderStr,
                                            String parentFullPath,
                                            List<PmAuthority> itemList) {
        Ordering<PmAuthority> ordering = Ordering
                .natural()
                .nullsFirst()
                .onResultOf(person -> person.getOrderCode());

        List<PmAuthority> childrenList = itemList
                .stream()
                .filter(i -> i.getParentId().equals(parentId))
                .sorted(ordering)
                .collect(Collectors.toList());

        for (int index = 0; index < childrenList.size(); index++) {
            PmAuthority child = childrenList.get(index);
            Integer temp = ((index + 1) * 2);
            String childPadStr = Strings.padStart(temp.toString(), 3, '0');
            if (child.getName().equals("root")) {
                child.setOrderCode("");
                child.setFullPath("");
            } else {
                String orderPreStr = "";
                String pathPreStr = "";
                if (!parentOrderStr.equals("")) {
                    orderPreStr = parentOrderStr + ".";
                }
                if (!parentFullPath.equals("")) {
                    pathPreStr = parentFullPath + ".";
                }
                child.setOrderCode(childPadStr);
                child.setFullPath(pathPreStr + child.getName());
            }

            reGenerateOrderAndFullPath(child.getId(), child.getOrderCode(), child.getFullPath(), itemList);
        }
    }

    /**
     * 生成树
     *
     * @param list
     * @param id
     * @return
     */
    private List<PmAuthority> genTreeById(List<PmAuthority> list, String id) {
        List<PmAuthority> resultList = new ArrayList<>();
        if (id != null) {
            PmAuthority data = list.stream()
                    .filter(i -> i.getId().equals(id))
                    .findFirst()
                    .orElseGet(null);
            resultList.add(data);
        }
        List<PmAuthority> childrenList = list.stream()
                .filter(i -> i.getParentId().equals(id))
                .collect(Collectors.toList());
        for (var childData : childrenList) {
            var l = genTreeById(list, childData.getId());
            resultList.addAll(l);
        }
        return resultList;
    }

    /**
     * 是否是子节点
     *
     * @param id
     * @param childId
     * @return
     */
    private Boolean isChild(String id, String childId) {
        List<PmAuthority> list = getTreeList(id);
        Optional<PmAuthority> child = list.stream()
                .filter(i -> i.getId().equals(childId))
                .findFirst();
        return child.isPresent();
    }

    /**
     * 获取直接子节点
     *
     * @param id
     * @return
     */
    private List<PmAuthority> getDirectChildren(String id) {
        PmAuthorityExample example = new PmAuthorityExample();
        PmAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        return authorityMapper.selectByExample(example);
    }

    /**
     * 级联删除
     *
     * @param parentId
     */
    private void deleteByParentId(String parentId) {
        PmAuthorityExample example = new PmAuthorityExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<PmAuthority> itemList = authorityMapper
                .selectByExample(example);
        for (var item : itemList) {
            deleteByParentId(item.getId());
            simpleDelete(item.getId());
        }
    }

    private int simpleDelete(String id) {
        PmRoleAndAuthorityExample example = new PmRoleAndAuthorityExample();
        example.createCriteria().andAuthorityIdEqualTo(id);
        roleAndAuthorityMapper.deleteByExample(example);
        return authorityMapper.deleteByPrimaryKey(id);
    }

    //endregion
}


