package com.enterprise.forum.exception;

/**
 * @author Jiayi Zhu
 * 2022/12/21
 */
public class BusinessException extends ForumException {

    public static BusinessException UsernameExisted = new BusinessException("用户名已存在");

    public static BusinessException UserNotFound = new BusinessException("找不到该用户");

    public static BusinessException TopicNotFound = new BusinessException("找不到主题帖");


    private final Integer code;

    public BusinessException(Integer code, String message) {

        super(message);
        this.code = code;
    }

    public BusinessException(String message) {

        this(-1, message);
    }

    public Integer getCode() {

        return code;
    }
}
