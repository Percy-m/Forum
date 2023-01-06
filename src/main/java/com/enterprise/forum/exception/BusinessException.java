package com.enterprise.forum.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2022/12/21
 */
@Getter
public class BusinessException extends ForumException {

    public static BusinessException UsernameExisted
            = new BusinessException(HttpStatus.BAD_REQUEST, "用户名已存在");

    public static BusinessException UserNotFound
            = new BusinessException(HttpStatus.NOT_FOUND, "找不到该用户");

    public static BusinessException TopicNotFound
            = new BusinessException(HttpStatus.NOT_FOUND, "找不到主题帖");

    public static BusinessException PermissionDenied
            = new BusinessException(HttpStatus.FORBIDDEN, "没有权限，拒绝访问");


    public static BusinessException ActionNotAllowed
            = new BusinessException(HttpStatus.FORBIDDEN, "不允许的操作");

    public BusinessException(int code, String message) {

        super(code, message);
    }

    public BusinessException(HttpStatus status, String message) {

        this(status.value(), message);
    }

    public HttpStatus getStatus() {

        return HttpStatus.valueOf(getCode());
    }
}
