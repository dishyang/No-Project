package com.example.noProject.config.security.filter;


import com.example.noProject.config.security.entities.SimpleAuthority;
import com.example.noProject.constants.BussinessConstants;
import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import com.example.noProject.service.redis.RedisService;
import com.example.noProject.utils.JsonUtil;
import com.example.noProject.utils.JwtUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class TokenSecurityFilter extends OncePerRequestFilter {
    @Resource
    RedisService redisService;
    @Resource
    JwtUtil jwtUtil;

    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String userJson =  (String)redisService.getObjectFormSessionByKey(request, BussinessConstants.TOKEN_USER_KEY);
        if (userJson == null){
            filterChain.doFilter(request,response);
            return;
        }
        SystemUser systemUser = JsonUtil.jsonToObject(userJson, SystemUser.class);
        String authoritiesJson =  (String)redisService.getObjectFormSessionByKey(request, BussinessConstants.TOKEN_AUTHORITIES_KEY);
        if(authoritiesJson == null){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(systemUser,null,null));
            filterChain.doFilter(request,response);
            return;
        }
        List<SimpleAuthority> authorityList = JsonUtil.jsonToList(authoritiesJson, new TypeReference<List<SimpleAuthority>>(){});
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(systemUser,null,authorityList));
        filterChain.doFilter(request,response);
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //验证Token
        String token = request.getHeader(BussinessConstants.ACCESS_TOKEN);
        Jws<Claims> claimsJws = jwtUtil.parseToken(token);
        if(claimsJws == null){
            filterChain.doFilter(request,response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,null,null));
        filterChain.doFilter(request,response);
    }
}
