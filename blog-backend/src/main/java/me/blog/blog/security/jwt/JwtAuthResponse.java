package me.blog.blog.security.jwt;

import me.blog.blog.model.BaseModel;

/**
 * @author Hilal
 */
public class JwtAuthResponse extends BaseModel {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}