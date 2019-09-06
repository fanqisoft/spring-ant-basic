package cn.coreqi.server.service;

import cn.coreqi.server.annotation.DepartmentDataSecurity;
import cn.coreqi.server.model.ControllerMethodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;


    @Override
    public List<ControllerMethodModel> getControllerMethodList() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        List<ControllerMethodModel> res = new ArrayList<>();

        handlerMethods.forEach((RequestMappingInfo i, HandlerMethod j) -> {
            String controllerName = j.getBean().toString();
            String methodName = j.getMethod().getName();
            String mapUrl = i.getPatternsCondition().getPatterns().stream().findFirst().orElse("");


            ControllerMethodModel controllerModel = new ControllerMethodModel();
            controllerModel.setFullPath(controllerName);
            controllerModel.setId(controllerModel.getFullPath());
            controllerModel.setName(controllerName);
            controllerModel.setParentId(null);
            controllerModel.setType("controller");

            ControllerMethodModel methodModel = new ControllerMethodModel();
            methodModel.setFullPath(controllerModel.getFullPath() + "." + methodName);
            methodModel.setId(methodModel.getFullPath());
            methodModel.setName(methodName);
            methodModel.setMapUrl(mapUrl);
            methodModel.setParentId(controllerModel.getId());
            methodModel.setType("method");


            DepartmentDataSecurity dataSecurity = null;
            Optional<MethodParameter> param = Arrays.stream(j.getMethodParameters())
                    .filter(k -> k.hasParameterAnnotation(DepartmentDataSecurity.class))
                    .findFirst();
            if (param.isPresent()) {
                dataSecurity = param.get().getParameterAnnotation(DepartmentDataSecurity.class);
            }

            ControllerMethodModel dataSecurityModel = null;
            if (dataSecurity != null) {
                dataSecurityModel = new ControllerMethodModel();
                dataSecurityModel.setName(dataSecurity.value() + "DataSecurity");
                dataSecurityModel.setFullPath(methodModel.getFullPath() + "." + dataSecurityModel.getName());
                dataSecurityModel.setId(dataSecurityModel.getFullPath());
                dataSecurityModel.setParentId(methodModel.getId());
                dataSecurityModel.setMapUrl(null);
                dataSecurityModel.setType("dataSecurity");
            }


            Optional<ControllerMethodModel> repeatItem = res.stream().filter(k -> k.getId().equals(controllerName))
                    .findFirst();

            if (!repeatItem.isPresent()) {
                res.add(controllerModel);
            }
            res.add(methodModel);

            if (dataSecurityModel != null) {
                res.add(dataSecurityModel);
            }
        });

        return res;
    }
}
