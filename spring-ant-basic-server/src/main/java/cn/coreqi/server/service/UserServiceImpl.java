package cn.coreqi.server.service;

import cn.coreqi.server.core.MainUtil;
import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.*;
import cn.coreqi.server.model.PmUserModel;
import cn.coreqi.server.repository.entityMapper.PmUserAndRoleMapper;
import cn.coreqi.server.repository.entityMapper.PmUserMapper;
import cn.coreqi.server.repository.modelMapper.UserModelMapper;
import cn.coreqi.server.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmUserMapper userMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PmUserAndRoleMapper userAndRoleMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserModelMapper userModelMapper;


    @Override
    public PageInfo<PmUserModel> getList(String loginName, String nickName, String departmentId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(), MainUtil.GetPageHelperOrdersStr(pageable.getSort()));
        return new PageInfo<>(userModelMapper.selectAll(loginName, nickName, departmentId));
    }

    @Override
    public PmUserModel getById(String id) {
        return userModelMapper.selectById(id);
    }

    @Override
    public PmUser create(PmUser entity) throws ProjectException {
        entity.setId(UUID.randomUUID().toString());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());

        int affectRow = userMapper.insert(entity);
        if (affectRow == 0) {
            throw new ProjectException("创建失败");
        }

        return userMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    public PmUser update(PmUser entity) throws ProjectException {
        PmUser getEntity = userMapper.selectByPrimaryKey(entity.getId());

        entity.setCreateTime(getEntity.getCreateTime());
        entity.setUpdateTime(LocalDateTime.now());

        int affectRow = userMapper.updateByPrimaryKey(entity);
        if (affectRow == 0) {
            throw new ProjectException("更新失败");
        }
        return userMapper.selectByPrimaryKey(entity.getId());
    }

    @Override
    public PmUser delete(String id) throws ProjectException {
        PmUser deleteData = getById(id);
        if (deleteData == null) {
            throw new ProjectException("数据不存在,请刷新重试");
        }

        int affectRow = userMapper.deleteByPrimaryKey(deleteData.getId());
        if (affectRow == 0) {
            throw new ProjectException("删除失败");
        }
        return deleteData;
    }


    @Override
    public void updateUserAndRole(String userId, List<String> roleIdList) {
        PmUserAndRoleExample example = new PmUserAndRoleExample();
        PmUserAndRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);

        userAndRoleMapper.deleteByExample(example);

        for (String roleId : roleIdList) {
            PmUserAndRole item = new PmUserAndRole();
            item.setRoleId(roleId);
            item.setUserId(userId);
            userAndRoleMapper.insert(item);
        }
    }

    @Override
    public PmUser getByLoginName(String loginName) {
        PmUserExample example = new PmUserExample();
        PmUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);

        return userMapper
                .selectByExample(example)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PmAuthority> getUserAuthorityList(String userId) {
        return userModelMapper.selectAuthorityByUserId(userId);
    }


    @Override
    public List<PmRole> getUserRoleList(String userId) {
        return userModelMapper.selectRoleByUserId(userId);
    }
}


