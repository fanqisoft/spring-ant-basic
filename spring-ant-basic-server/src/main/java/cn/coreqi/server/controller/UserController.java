package cn.coreqi.server.controller;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.core.ResWrapper;
import cn.coreqi.server.entity.PmUser;
import cn.coreqi.server.model.PmUserModel;
import cn.coreqi.server.security.CustomUserDetails;
import cn.coreqi.server.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取所有项
     *
     * @param pageable
     * @return
     */
    @GetMapping(path = "/getList")
    public ResWrapper getList(
            @RequestParam(required = false) String loginName,
            @RequestParam(required = false) String nickName,
            @RequestParam(required = false) String departmentId,
            @PageableDefault(
                    size = 10,
                    page = 1,
                    sort = {"createTime"},
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        PageInfo<PmUserModel> data = userService.getList(loginName, nickName, departmentId, pageable);
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
        PmUser data = userService.getById(id);
        return ResWrapper.Success(data);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    @GetMapping(path = "/getCurrent")
    public ResWrapper getCurrent(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        Map<String, String> data = new HashMap<>();
        data.put("username", username);
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
    public ResWrapper create(@RequestBody PmUser entity) throws ProjectException {
        PmUser data = userService.create(entity);
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
    public ResWrapper update(@RequestBody PmUser entity) throws ProjectException {
        PmUser data = userService.update(entity);
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
        PmUser data = userService.delete(paramMap.get("id"));
        return ResWrapper.Success(data);
    }

    /**
     * 更新用户角色
     *
     * @param paramMap
     * @return
     */
    @PostMapping(path = "/updateUserAndRole")
    public ResWrapper updateUserAndRole(@RequestBody Map<String, Object> paramMap) {
        String userId = (String) paramMap.get("userId");
        List<String> roleIdList = (List<String>) paramMap.get("roleIdList");

        userService.updateUserAndRole(userId, roleIdList);
        return ResWrapper.Success();
    }
}
