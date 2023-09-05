package com.example.noProject.controller;


import com.example.noProject.config.security.entities.LoginUser;
import com.example.noProject.constants.BussinessConstants;
import com.example.noProject.constants.RequestResultConstants;
import com.example.noProject.criterion.data.BaseResponse;
import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "系统用户相关")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public BaseResponse login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ){
        BaseResponse baseResponse = new BaseResponse();
        log.debug("访问登录接口'/user/login',用户名:{},密码:{}",userName,password);
        //交由身份认证管理器进行身份认证，认证成功返回封装在UsernamePasswordAuthenticationToken里的认证信息
        Authentication usernamePasswordAuthenticationToken = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (usernamePasswordAuthenticationToken == null) {
            return baseResponse;
        }
        //UsernamePasswordAuthenticationToken的principal属性保存的账号，已经变成UserDetails
        LoginUser loginUser = (LoginUser) usernamePasswordAuthenticationToken.getPrincipal();
        return baseResponse;
    }

    @Operation(summary = "获取登录的信息")
    @GetMapping("/userInfo")
    public BaseResponse userInfo(HttpServletRequest request) {
        BaseResponse res = new BaseResponse();
        SystemUser systemUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*
        菜单树构建交由前端完成
        //封装菜单
        MenuTree menuTree = new MenuTree();
        List<SysMenus> tree = menuTree.buildTree(sysUser.getMenus());*/

        Map<String, Object> data = new HashMap<>();
        data.put("token", request.getHeader(BussinessConstants.ACCESS_TOKEN));
        data.put("user", systemUser);
        //data.put("menu",tree);
        res.setCode(RequestResultConstants.SERVICE_SUCCESS_CODE);
        res.setMessage(RequestResultConstants.SERVICE_SUCCESS_MSG);
        res.setData(data);
        return res;
    }

}
