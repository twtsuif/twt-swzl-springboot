package com.suif.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * 自定义token
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String jwt){
        this.token=jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
