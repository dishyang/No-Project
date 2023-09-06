package com.example.noProject.exception;

import com.example.noProject.constants.RequestResultConstants;
import com.example.noProject.criterion.data.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理器
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理认证异常
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public BaseResponse usernameNotFountExceptionHandler(Exception e) {
        return new BaseResponse(RequestResultConstants.AUTHENTICATION_FAIL_CODE,e.getMessage());
    }

    /**
     *处理身份验证对象不持有所需的权限的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public BaseResponse accessDeniedExceptionHandler(Exception e) {
        return new BaseResponse(RequestResultConstants.PERMISSION_DENIED_CODE,RequestResultConstants.PERMISSION_DENIED_MSG);
    }

    /**
     * 所有异常处理
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public BaseResponse defultHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("统一异常处理:", e);
        return new BaseResponse(RequestResultConstants.SERVICE_SYSTEM_ERROR_CODE,RequestResultConstants.SERVICE_SYSTEM_ERROR_MSG);
    }
}
