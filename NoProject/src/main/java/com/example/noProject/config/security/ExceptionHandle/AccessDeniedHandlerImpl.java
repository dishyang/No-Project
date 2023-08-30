package com.example.noProject.config.security.ExceptionHandle;


import com.example.noProject.constants.RequestResultConstants;
import com.example.noProject.criterion.data.BaseResponse;
import com.example.noProject.utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        BaseResponse res = new BaseResponse();
        res.setCode(RequestResultConstants.PERMISSION_DENIED_CODE);
        res.setMessage(RequestResultConstants.PERMISSION_DENIED_MSG);
        try {
            String resJson = JsonUtil.toJson(res);
            response.getWriter().write(resJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.getWriter().write("");
    }
}
