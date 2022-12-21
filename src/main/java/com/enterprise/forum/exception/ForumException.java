package com.enterprise.forum.exception;

/**
 * @author Jiayi Zhu
 * 2022/12/21
 */
public class ForumException extends RuntimeException{

    public ForumException(String message) {

        super(message);
    }

    public ForumException() {

        super();
    }

}
