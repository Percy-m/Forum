package com.enterprise.forum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2022/12/19
 */
@Getter
public class JwtAuthException extends ForumException{

    public static JwtAuthException RefreshTokenNotFound
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token Not Found");

    public static JwtAuthException RefreshTokenNotMatch
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token Not Match");

    public static JwtAuthException RefreshTokenExpired
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token is Expired");

    public JwtAuthException(int code, String message) {

        super(code, message);
    }

    public JwtAuthException(HttpStatus status, String message) {

        this(status.value(), message);
    }

    public HttpStatus getStatus() {

        return HttpStatus.valueOf(getCode());
    }

}
