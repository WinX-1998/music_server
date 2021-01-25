package com.example.music.Exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Jiangweixin
 * @Date: 2021/01/25/14:21
 * @Description:
 */
@ControllerAdvice
public class GloableExceptionResolver {
    // 没有权限时抛出的异常
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public void handleShiroException(HttpServletResponse resp) throws IOException {
        resp.setStatus(601);
        resp.getWriter().append("U do not have the power to do this.");
    }

    // 权限校验失败时抛出的异常
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public void AuthorizationException(HttpServletResponse resp) throws IOException {
        resp.setStatus(602);
        resp.getWriter().append("the power check is failed somehow, please logout and login and try again.");
    }

}
