package com.example.noProject.config.security.service;

import com.example.noProject.config.security.entities.LoginUser;
import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import com.example.noProject.service.user.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户认证处理类
 */
@Component
@Slf4j
public class CustomerUserDetailsServiceRF implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("登录验证用户名：{}", username);
        SystemUser sysUser = userService.getUserByName(username);
        if (sysUser == null) {
            log.debug("登录验证用户名不存在");
        }
        /*List<SysMenus> menusList = menuService.getMenusByUserId(sysUser);
        if(menusList != null && menusList.size()>0){
            //封装权限集合
            sysUser.setMenus(menusList);
            List<SimpleAuthority> permissionCodeList = new ArrayList<>(menusList.size());
            for (SysMenus menu: menusList) {
                if(menu.getCode() != null){
                    permissionCodeList.add(new SimpleAuthority(menu.getCode()));
                }
            }
            return new LoginUser(sysUser,permissionCodeList);
        }*/
        return new LoginUser(sysUser, null);
    }
}
