package cn.coreqi.server.security;

import cn.coreqi.server.annotation.DepartmentDataSecurity;
import cn.coreqi.server.enums.AuthorityTypeEnum;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        boolean hasAnnotation = methodParameter.hasParameterAnnotation(DepartmentDataSecurity.class);
        return hasAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String controllerName = methodParameter
                .getMethod()
                .getDeclaringClass()
                .getSimpleName();
        String methodName = methodParameter
                .getMethod()
                .getName();

        String departmentAuthStr = AuthorityTypeEnum.Server.getRootName()
                + "."
                + controllerName
                + "."
                + methodName
                + "."
                + "departmentDataSecurity";


        List<GrantedAuthority> userAuthorities = new ArrayList<>(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities());

        boolean hasAuth = userAuthorities
                .stream()
                .anyMatch(i -> i.getAuthority().toLowerCase().equals(departmentAuthStr.toLowerCase()));
        return hasAuth;
    }
}
