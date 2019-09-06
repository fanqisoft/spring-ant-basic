package cn.coreqi.server.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    @Getter
    private String departmentId;

    public CustomUserDetails(String username, String password, String departmentId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.departmentId = departmentId;
    }
}
