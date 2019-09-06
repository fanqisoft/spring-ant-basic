package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmAuthority;
import cn.coreqi.server.entity.PmRole;
import cn.coreqi.server.entity.PmUser;
import cn.coreqi.server.model.PmUserModel;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /**
     * 获取所有项
     *
     * @param loginName
     * @param nickName
     * @param departmentId
     * @param pageable
     * @return
     */
    PageInfo<PmUserModel> getList(String loginName, String nickName, String departmentId, Pageable pageable);

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    PmUserModel getById(String id);

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmUser create(PmUser entity) throws ProjectException;

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmUser update(PmUser entity) throws ProjectException;

    /**
     * 删除对象
     *
     * @param id
     * @return
     * @throws ProjectException
     */
    PmUser delete(String id) throws ProjectException;

    void updateUserAndRole(String userId, List<String> roleIdList);

    PmUser getByLoginName(String loginName);

    List<PmAuthority> getUserAuthorityList(String userId);

    List<PmRole> getUserRoleList(String userId);
}
