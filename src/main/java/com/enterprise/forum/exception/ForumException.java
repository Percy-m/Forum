package com.enterprise.forum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2022/12/21
 */
@Getter
public abstract class ForumException extends RuntimeException{

    private final int code;

    public ForumException(int code, String message) {

        super(message);
        this.code = code;
    }

    public abstract HttpStatus getStatus();
}
