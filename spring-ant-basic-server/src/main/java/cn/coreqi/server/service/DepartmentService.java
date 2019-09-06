package cn.coreqi.server.service;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.entity.PmDepartment;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    /**
     * 获取列表项
     *
     * @param pageable
     * @return
     */
    PageInfo<PmDepartment> getList(String name, String description, Pageable pageable);

    /**
     * 获取所有项
     * @return
     */
    List<PmDepartment> getAll();

    List<PmDepartment> getAll(String departmentId);

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    PmDepartment getById(String id);

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmDepartment create(PmDepartment entity) throws ProjectException;

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    PmDepartment update(PmDepartment entity) throws ProjectException;

    /**
     * 删除对象
     *
     * @param id
     * @return
     * @throws ProjectException
     */
    PmDepartment delete(String id) throws ProjectException;
}
