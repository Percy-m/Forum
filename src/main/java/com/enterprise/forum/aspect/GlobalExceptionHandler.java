package com.enterprise.forum.aspect;

import com.enterprise.forum.vo.CommonVO;
import jakarta.validation.ValidationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Jiayi Zhu
 * 12/24/2022
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public CommonVO handleBindException(BindException e) {

        return CommonVO.error("BindException: " + e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public CommonVO handleValidationException(ValidationException e) {

        return CommonVO.error("ValidationException: " + e.getMessage());
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.class)
//    public CommonVO handle() {
//
//        return CommonVO.error("401");
//    }

}
