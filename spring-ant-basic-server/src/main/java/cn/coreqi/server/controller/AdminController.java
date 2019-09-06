package cn.coreqi.server.controller;

import cn.coreqi.server.core.ResWrapper;
import cn.coreqi.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping(path = "/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/getControllerMethodList")
    public ResWrapper getControllerList() {
        return ResWrapper.Success(adminService.getControllerMethodList());
    }
}

