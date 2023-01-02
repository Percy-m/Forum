package com.enterprise.forum.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * 1/1/2023
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenDTO {

    private String accessToken;

    private String refreshToken;

    public static TokenDTO of(String accessToken, String refreshToken) {

        return new TokenDTO(accessToken, refreshToken);
    }

}
