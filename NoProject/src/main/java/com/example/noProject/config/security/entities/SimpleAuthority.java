package com.example.noProject.config.security.entities;

import org.springframework.security.core.GrantedAuthority;

public class SimpleAuthority implements GrantedAuthority {

    private String authority;

    public SimpleAuthority() {
    }

    public SimpleAuthority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SimpleAuthority) {
            return this.authority.equals(((SimpleAuthority) obj).authority);
        }
        return false;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
