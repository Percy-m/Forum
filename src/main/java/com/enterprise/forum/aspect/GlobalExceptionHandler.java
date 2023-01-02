package com.enterprise.forum.aspect;

import com.enterprise.forum.exception.JwtAuthException;
import com.enterprise.forum.vo.CommonVO;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
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

        return CommonVO.badRequest("BindException: " + e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public CommonVO handleValidationException(ValidationException e) {

        return CommonVO.badRequest("ValidationException: " + e.getMessage());
    }

    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<CommonVO> handleJwtAuthException(JwtAuthException e) {

        return new ResponseEntity<>(
                CommonVO.error(e.getStatus(), e.getMessage()),
                e.getStatus());
    }


}
