package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmRole;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
    PageInfo<PmRole> getList(Pageable pageable);

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    PmRole getById(String id);

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmRole create(PmRole entity) throws ProjectException;

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmRole update(PmRole entity) throws ProjectException;

    /**
     * 删除对象
     *
     * @param id
     * @return
     * @throws ProjectException
     */
    PmRole delete(String id) throws ProjectException;

    List<PmRole> getAll();

    /**
     * 更新角色拥有的权限
     * @param roleId
     * @param authorityIdList
     */
    void updateRoleAndAuthority(String roleId, List<String> authorityIdList);

    List<PmRole> getListByUserId(String userId);
}
