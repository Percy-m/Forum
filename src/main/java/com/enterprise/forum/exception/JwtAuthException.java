package com.enterprise.forum.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2022/12/19
 */

public class JwtAuthException extends ForumException{

    private final HttpStatus status;

    public JwtAuthException(HttpStatus status, String message) {

        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {

        return status;
    }
}
