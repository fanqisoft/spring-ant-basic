package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmAuthority;
import cn.coreqi.server.model.PmAuthorityModel;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    List<PmAuthority> getList();

    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
    PageInfo<PmAuthority> getList(Pageable pageable);

    List<PmAuthorityModel> getAll();

    List<PmAuthority> getTreeList();

    List<PmAuthority> getTreeList(String id);

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    PmAuthority getById(String id);

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmAuthority create(PmAuthority entity) throws ProjectException;

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmAuthority update(PmAuthority entity) throws ProjectException;

    /**
     * 删除对象
     *
     * @param id
     * @return
     * @throws ProjectException
     */
    PmAuthority delete(String id) throws ProjectException;

    PmAuthority delete(String id, Boolean isTreeDelete) throws ProjectException;

    /**
     * 通过roleId获取列表
     *
     * @param roleId
     * @return
     */
    List<PmAuthority> getListByRoleId(String roleId);


    /**
     * 生成服务器端权限
     */
    void generateServerAuthority();
}
