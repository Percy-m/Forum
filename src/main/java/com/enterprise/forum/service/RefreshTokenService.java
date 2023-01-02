package com.enterprise.forum.service;

import com.enterprise.forum.exception.JwtAuthException;

/**
 * Save refresh-token
 *
 * @author Jiayi Zhu
 * 1/1/2023
 */
public interface RefreshTokenService {

    void saveRefreshToken(long id, String refreshToken);

    String getRefreshTokenById(long id) throws JwtAuthException;
}
