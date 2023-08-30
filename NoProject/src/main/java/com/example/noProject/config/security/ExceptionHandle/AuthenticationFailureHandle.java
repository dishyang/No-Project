package com.example.noProject.config.security.ExceptionHandle;

import com.example.noProject.constants.RequestResultConstants;
import com.example.noProject.criterion.data.BaseResponse;
import com.example.noProject.utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class AuthenticationFailureHandle implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        BaseResponse res = new BaseResponse();
        res.setCode(RequestResultConstants.AUTHENTICATION_FAIL_CODE);
        res.setMessage(RequestResultConstants.AUTHENTICATION_FAIL_MSG);
        String resJson = JsonUtil.toJson(res);
        response.getWriter().write(resJson);
    }
}
