package com.enterprise.forum.service.impl;

import com.enterprise.forum.domain.security.RefreshToken;
import com.enterprise.forum.exception.JwtAuthException;
import com.enterprise.forum.repository.RefreshTokenRepository;
import com.enterprise.forum.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiayi Zhu
 * 1/1/2023
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public void setRefreshTokenRepository(RefreshTokenRepository refreshTokenRepository) {

        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void saveRefreshToken(long id, String refreshToken) {


        RefreshToken rToken = RefreshToken.of(id, refreshToken);
        refreshTokenRepository.save(rToken);
    }

    @Override
    public String getRefreshTokenById(long id) throws JwtAuthException {

        RefreshToken refreshToken = refreshTokenRepository.findById(id)
                .orElseThrow(() -> JwtAuthException.RefreshTokenNotFound);

        return refreshToken.getToken();
    }
}
