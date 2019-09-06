package cn.coreqi.server.enums;

import lombok.Getter;
import lombok.Setter;

public enum AuthorityTypeEnum {
    Server(1, "server", "服务器端权限"),
    Client(2, "client", "客户端权限");

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String rootName;


    AuthorityTypeEnum(int id, String rootName, String description) {
        this.id = id;
        this.description = description;
        this.rootName = rootName;
    }
}