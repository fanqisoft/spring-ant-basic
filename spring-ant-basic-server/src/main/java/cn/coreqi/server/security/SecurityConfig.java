package cn.coreqi.server.security;

import cn.coreqi.server.entity.PmAuthority;
import cn.coreqi.server.service.AuthorityService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CustomEntryPoint customEntryPoint;

    /**
     * 密码加密
     *
     * @return
     */
    private PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                boolean bol = rawPassword.toString().equals(encodedPassword);
                return bol;
            }

        };
    }


    /**
     * 配置权限数据存储
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<PmAuthority> authorityList = authorityService.getList();
        List<PmAuthority> urlAuthorityList = authorityList.stream()
                .filter(i -> !Strings.isNullOrEmpty(i.getVirtualUrl()))
                .collect(Collectors.toList());

//        http.authorizeRequests().anyRequest().permitAll();
//        return;

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.
                ExpressionInterceptUrlRegistry interceptUrlRegistry = http
                .authorizeRequests();
        for (PmAuthority urlAuthority : urlAuthorityList) {
            interceptUrlRegistry = interceptUrlRegistry
                    .antMatchers(urlAuthority.getVirtualUrl())
                    .hasAuthority(urlAuthority.getFullPath());
        }

        http = interceptUrlRegistry
                .anyRequest()
                .permitAll()
                .and();

        http = http
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(new CustomLoginFailHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .permitAll()
                .and();

        http = http
                .anonymous()
                .disable();


        http = http.exceptionHandling()
                .authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )
                .and();

        http.csrf().disable();

//        http.exceptionHandling().authenticationEntryPoint(customEntryPoint);

    }
}


