package ru.posmanager.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    IMPLEMENTOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}