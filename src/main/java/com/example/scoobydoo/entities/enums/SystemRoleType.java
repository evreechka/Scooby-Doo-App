package com.example.scoobydoo.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum SystemRoleType implements GrantedAuthority {
    USER,SHERIFF,ADMIN,INVESTIGATOR;
    @Override
    public String getAuthority() {
        return name();
    }
}
