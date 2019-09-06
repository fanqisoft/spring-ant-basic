package cn.coreqi.server.controller;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.core.ResWrapper;
import cn.coreqi.server.entity.PmRole;
import cn.coreqi.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/getList")
    public ResWrapper getList(
            @PageableDefault(
                    size = 10,
                    page = 1,
                    sort = {"createTime"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        return ResWrapper.Success(roleService.getList(pageable));
    }

    /**
     * 获取所有项
     *
     * @return
     */
    @GetMapping(path = "/getAll")
    @ResponseBody
    public ResWrapper getAll() throws Exception {
        List<PmRole> data = roleService.getAll();
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
        return ResWrapper.Success(roleService.getById(id));
    }

    /**
     * 创建对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    @PostMapping(path = "/create")
    public ResWrapper create(@RequestBody PmRole entity) throws ProjectException {
        return ResWrapper.Success(roleService.create(entity));
    }

    /**
     * 更新对象
     *
     * @param entity
     * @return
     * @throws ProjectException
     */
    @PostMapping(path = "/update")
    public ResWrapper update(@RequestBody PmRole entity) throws ProjectException {
        return ResWrapper.Success(roleService.update(entity));
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
        return ResWrapper.Success(roleService.delete(paramMap.get("id")));
    }

    /**
     * 更新角色权限
     *
     * @param paramMap
     * @return
     */
    @PostMapping(path = "/updateRoleAndAuthority")
    public ResWrapper updateRoleAndAuthority(@RequestBody Map<String, Object> paramMap) {
        String roleId = (String) paramMap.get("roleId");
        List<String> authorityIdList = (List<String>) paramMap.get("authorityIdList");

        roleService.updateRoleAndAuthority(roleId, authorityIdList);
        return ResWrapper.Success();
    }

    /**
     * 通过UserId获取权限列表
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/getListByUserId")
    public ResWrapper getListByUserId(@RequestParam String userId) {
        return ResWrapper.Success(roleService.getListByUserId(userId));
    }
}
