package cn.coreqi.server.error;

import cn.coreqi.server.core.ProjectException;
import cn.coreqi.server.core.ResWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主要用途：统一处理错误/异常(针对控制层)
 * 使用说明:
 * {@link ControllerAdvice 默认扫描路径：例如com.hehe.controller}
 * {@link ExceptionHandler 指定捕捉异常}
 * {@link ModelAndView 返回异常信息页(View)}
 * {@link ResponseBody 返回异常信息(JSON)}
 * <p>
 * 使用@ExceptionHandler时候需注意如下几点：
 * 1.获取异常：直接在方法参数注入
 * 2.常见缺点：无法捕捉404类异常
 * 3.替代方案：实现ErrorController
 *
 * @author yizhiwazi
 */

@ControllerAdvice
public class GlobalErrorHandler {
    /**
     * 错误信息页
     */
    private final static String DEFAULT_ERROR_VIEW = "error";

    /**
     * 错误信息构建器
     */
    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;

    /**
     * 根据业务规则,统一处理异常。
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest request, Exception error) {

        ErrorInfo errorInfo = errorInfoBuilder.getErrorInfo(request, error);

        //业务异常,返回200,前端判断错误,进行处理
        if (error instanceof ProjectException) {
            errorInfo.setStatusCode(HttpStatus.OK.value());
            ResWrapper resWrapper = ResWrapper.BusinessError(error.getMessage(), ((ProjectException) error).getMessageList());
            return ResponseEntity.status(errorInfo.getStatusCode()).body(resWrapper);
        }

        //显式捕捉404
        if (error instanceof NoHandlerFoundException) {
            errorInfo.setStatusCode(HttpStatus.NOT_FOUND.value());
        }


        //显式捕捉403 org.springframework.security.access.AccessDeniedException
        if (error instanceof AccessDeniedException) {
            errorInfo.setStatusCode(HttpStatus.FORBIDDEN.value());
        }

        //其他异常直接返回
        return ResponseEntity.status(errorInfo.getStatusCode()).body(errorInfo);
    }
}
