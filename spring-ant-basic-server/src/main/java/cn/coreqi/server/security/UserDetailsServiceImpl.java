package cn.coreqi.server.security;

import cn.coreqi.server.entity.PmAuthority;
import cn.coreqi.server.entity.PmUser;
import cn.coreqi.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PmUser user = userService.getByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        //获取权限列表
        List<String> authoryList = userService.getUserAuthorityList(user.getId())
                .stream()
                .map(PmAuthority::getFullPath)
                .collect(Collectors.toList());

        //获取角色列表
        List<String> roleList = userService.getUserRoleList(user.getId())
                .stream()
                .map(pmRole -> "ROLE_" + pmRole.getName())
                .collect(Collectors.toList());

        //把角色加上前缀并入权限列表
        authoryList.addAll(roleList);

        List<GrantedAuthority> authories = authoryList
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getLoginName(),
                user.getPassword(),
                user.getMainDepartmentId(),
                authories
        );
    }
}
