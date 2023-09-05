package com.example.noProject.controller;


import com.example.noProject.config.security.entities.LoginUser;
import com.example.noProject.constants.BussinessConstants;
import com.example.noProject.constants.RequestResultConstants;
import com.example.noProject.criterion.data.BaseResponse;
import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import com.example.noProject.utils.JwtUtil;
import com.example.noProject.utils.UuidUtil;
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
    @Resource
    private JwtUtil jwtUtil;


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


        //认证成功生成Token
        String jwtId = UuidUtil.getUUID(); //作用：将此Token和Redis中的数据绑定
        Map<String,Object> claims = new HashMap<>();
        claims.put("userInfo",loginUser.getSysUser());
        String jwsToken = jwtUtil.createToken(claims,jwtId);


        Map<String, Object> data = new HashMap<>();
        data.put("access-token", jwsToken);
        baseResponse.setCode(RequestResultConstants.SERVICE_SUCCESS_CODE);
        baseResponse.setMessage(RequestResultConstants.SERVICE_SUCCESS_MSG);
        baseResponse.setData(data);
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


        res.setCode(RequestResultConstants.SERVICE_SUCCESS_CODE);
        res.setMessage(RequestResultConstants.SERVICE_SUCCESS_MSG);
        return res;
    }

}
