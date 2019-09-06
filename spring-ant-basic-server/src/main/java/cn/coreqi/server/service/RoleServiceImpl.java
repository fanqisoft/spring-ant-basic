package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.*;
import cn.coreqi.server.repository.entityMapper.PmRoleAndAuthorityMapper;
import cn.coreqi.server.repository.entityMapper.PmRoleMapper;
import cn.coreqi.server.repository.entityMapper.PmUserAndRoleMapper;
import cn.coreqi.server.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmRoleMapper roleMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmRoleAndAuthorityMapper roleAndAuthorityMapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmUserAndRoleMapper userAndRoleMapper;



    @Override
    public PageInfo<PmRole> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        return new PageInfo<>(roleMapper.selectByExample(null));
    }

    @Override
    public PmRole getById(String id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PmRole create(PmRole entity) throws ProjectException {
        entity.setId(UUID.randomUUID().toString());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        int affectRow = roleMapper.insert(entity);
        if (affectRow == 0) {
            throw new ProjectException("创建失败");
        }

        return roleMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    public PmRole update(PmRole entity) throws ProjectException {
        PmRole updateEntity = roleMapper.selectByPrimaryKey(entity.getId());

        updateEntity.setName(entity.getName());
        updateEntity.setFullPath(entity.getFullPath());
        updateEntity.setParentId(entity.getParentId());
        updateEntity.setDescription(entity.getDescription());
        updateEntity.setSummary(entity.getSummary());
        updateEntity.setUpdateTime(LocalDateTime.now());

        int affectRow = roleMapper.updateByPrimaryKey(updateEntity);
        if (affectRow == 0) {
            throw new ProjectException("更新失败");
        }
        return roleMapper.selectByPrimaryKey(updateEntity.getId());
    }

    @Override
    public PmRole delete(String id) throws ProjectException {
        PmRole deleteData = getById(id);
        if (deleteData == null) {
            throw new ProjectException("数据不存在,请刷新重试");
        }

        int affectRow = roleMapper.deleteByPrimaryKey(id);
        if (affectRow == 0) {
            throw new ProjectException("删除失败");
        }
        return deleteData;
    }

    @Override
    public List<PmRole> getAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public void updateRoleAndAuthority(String roleId, List<String> authorityIdList) {
        PmRoleAndAuthorityExample example = new PmRoleAndAuthorityExample();
        PmRoleAndAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);

        roleAndAuthorityMapper.deleteByExample(example);

        for (String authorityId : authorityIdList) {
            PmRoleAndAuthority item = new PmRoleAndAuthority();
            item.setAuthorityId(authorityId);
            item.setRoleId(roleId);
            roleAndAuthorityMapper.insert(item);
        }
    }

    @Override
    public List<PmRole> getListByUserId(String userId) {

        PmUserAndRoleExample userAndRoleExample= new PmUserAndRoleExample();
        PmUserAndRoleExample.Criteria userAndRoleCriteria = userAndRoleExample.createCriteria();
        userAndRoleCriteria.andUserIdEqualTo(userId);

        List<PmUserAndRole> raaList = userAndRoleMapper.selectByExample(userAndRoleExample);

        if (raaList.isEmpty()) {
            return new ArrayList<>();
        }
        PmRoleExample roleExample = new PmRoleExample();
        PmRoleExample.Criteria roleCriteria = roleExample.createCriteria();

        roleCriteria.andIdIn(raaList.stream().map(PmUserAndRole::getRoleId).collect(Collectors.toList()));

        return roleMapper.selectByExample(roleExample);
    }
}


