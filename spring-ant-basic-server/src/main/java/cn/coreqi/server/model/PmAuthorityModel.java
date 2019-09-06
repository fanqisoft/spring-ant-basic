package cn.coreqi.server.model;

import cn.coreqi.server.entity.PmAuthority;

public class PmAuthorityModel extends PmAuthority {
    private String typeStr;

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }
}
