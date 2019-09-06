package cn.coreqi.server.model;

import lombok.Data;

@Data
public class ControllerMethodModel {
    private String id;
    private String name;
    private String parentId;

    private String fullPath;
    private String mapUrl;

    private String type;
}
