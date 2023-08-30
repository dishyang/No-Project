package com.example.noProject.config.security.service;

import com.example.noProject.config.security.entities.LoginUser;
import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import com.example.noProject.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户认证处理类
 */
//@Component
public class CustomerUserDetailsServiceRF implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser sysUser = userService.getUserByName(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("userName not found");
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
