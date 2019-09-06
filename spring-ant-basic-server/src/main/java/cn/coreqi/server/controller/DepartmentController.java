package cn.coreqi.server.controller;

import cn.coreqi.server.annotation.DepartmentDataSecurity;
import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.core.ResWrapper;
import cn.coreqi.server.entity.PmDepartment;
import cn.coreqi.server.security.CustomUserDetails;
import cn.coreqi.server.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取列表项
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/getList")
    @ResponseBody
    public ResWrapper getList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @PageableDefault(
                    size = 10,
                    page = 1,
                    sort = {"createTime"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable) throws Exception {
        PageInfo<PmDepartment> data = departmentService.getList(name, description, pageable);
        return ResWrapper.Success(data);
    }

    /**
     * 获取所有项
     *
     * @return
     */
    @GetMapping(path = "/getAll")
    @ResponseBody
    public ResWrapper getAll(@AuthenticationPrincipal CustomUserDetails userDetails,
                             @DepartmentDataSecurity boolean isDepartmentLimit) {
        String departmentId = userDetails.getDepartmentId();

        List<PmDepartment> data;
        if (isDepartmentLimit) {
            data = departmentService.getAll(departmentId);
        } else {
            data = departmentService.getAll();
        }

        return ResWrapper.Success(data);
    }


    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/getById")
    public ResWrapper getById(@RequestParam String id) {
        PmDepartment data = departmentService.getById(id);
        return ResWrapper.Success(data);
    }

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    @PostMapping(path = "/create")
//    @ExceptionHandler({ProjectException.class})
    public ResWrapper create(@RequestBody PmDepartment entity) throws ProjectException {
        if (Strings.isNullOrEmpty(entity.getParentId())) {
            throw new ProjectException("父ID不能为空");
        }
        PmDepartment data = departmentService.create(entity);
        return ResWrapper.Success(data);
    }

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    @PostMapping(path = "/update")
    public ResWrapper update(@RequestBody PmDepartment entity) throws ProjectException {
        PmDepartment data = departmentService.update(entity);
        return ResWrapper.Success(data);
    }

    /**
     * 删除对象
     *
     * @param paramMap
     * @return
     * @throws ProjectException
     */
    @PostMapping(path = "/delete")
    public ResWrapper delete(@RequestBody Map<String, String> paramMap) throws ProjectException {
        PmDepartment deleteData = departmentService.delete(paramMap.get("id"));
        return ResWrapper.Success(deleteData);
    }
}
