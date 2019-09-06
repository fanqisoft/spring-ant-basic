package cn.coreqi.server.service;

import cn.coreqi.server.model.ControllerMethodModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    List<ControllerMethodModel> getControllerMethodList();
}
