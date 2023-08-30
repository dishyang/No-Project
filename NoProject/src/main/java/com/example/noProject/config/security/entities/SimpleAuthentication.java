package com.example.noProject.config.security.entities;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.Collection;

public class SimpleAuthentication extends AbstractAuthenticationToken {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal; // 用户

    private Object credentials; // 密码


    /**
     * 创建未经认证的的Authentication
     * @param principal
     * @param credentials
     */
    public SimpleAuthentication(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * 创建经过认证过成功的Authentication
     * @param principal    可为Null或者是一个UserDetails对象
     * @param credentials  可为Null值
     * @param authorities  必须传入一个鉴权信息
     */
    public SimpleAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    /**
     * 未授信凭据的静态工厂方法.
     * @param principal
     * @param credentials
     * @return
     */
    public static SimpleAuthentication unAuthentication(Object principal, Object credentials){
        return new SimpleAuthentication(principal,credentials);
    }

    /**
     * 授信凭据的静态工厂方法.
     * @param principal
     * @param credentials
     * @return
     */
    public static SimpleAuthentication authentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities){
        return new SimpleAuthentication(principal,credentials,authorities);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    /**
     * 清楚敏感信息的方法
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
