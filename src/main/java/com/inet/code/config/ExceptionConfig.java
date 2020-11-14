package com.inet.code.config;

import com.inet.code.utlis.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
/**
 * ExceptionConfig
 *
 * @author HCY
 * @since 2020/10/27
 */
@RestControllerAdvice
public class ExceptionConfig {
    /**
     * 捕获shiro的异常
     * @author HCY
     * @since 2020-10-11
     * @param exception 异常元素
     * @return Result
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException exception){
        return new Result(401,"Unauthorized","非法的",exception.getMessage(),"unauthorized");
    }

    /**
     * 捕获UnauthorizedException异常
     * @author HCY
     * @since 2020-10-11
     * @return Result
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handel401(){
        return new Result(401,"Unauthorized","非法的","无权限","unauthorized");
    }

    /**
     * 捕捉其他所有异常
     * @author HCY
     * @since 2020-10-11
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        System.out.println(request);
        return new Result(401,ex.getMessage(),"非法的",getStatus(request).value(),"unauthorized");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
