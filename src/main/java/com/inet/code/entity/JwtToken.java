package com.inet.code.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 *
 * @author HCY
 * @since 2020/10/27
 */
public class JwtToken implements AuthenticationToken {
    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
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
