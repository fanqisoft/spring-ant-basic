package cn.coreqi.server.controller;

import cn.coreqi.server.core.ResWrapper;
import cn.coreqi.server.entity.ParamAuthorityType;
import cn.coreqi.server.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/param")
@RestController
public class ParamController {
    @Autowired
    private ParamService paramService;

    @GetMapping(path = "/getAuthorityTypeList")
    public ResWrapper getAuthorityTypeList() {
        List<ParamAuthorityType> list = paramService.getAuthorityTypeList();
        return ResWrapper.Success(list);
    }
}
