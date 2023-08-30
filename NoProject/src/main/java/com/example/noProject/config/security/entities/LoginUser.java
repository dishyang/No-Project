package com.example.noProject.config.security.entities;


import com.example.noProject.dataSource.defaultDataSource.entities.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUser implements UserDetails {

    private SystemUser systemUser;

    private Collection<? extends GrantedAuthority> authorities;



    public LoginUser(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
        this.systemUser = systemUser;
        this.authorities = authorities;
    }

    public SystemUser getSysUser() {
        return systemUser;
    }

    public void setSysUser(SystemUser sysUser) {
        this.systemUser = sysUser;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUserName();
    }

    //是否没过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否没锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //凭证是否没过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否启用
    @Override
    public boolean isEnabled() {
        return systemUser.isStatus();
    }

}
