package com.bingo.admin.commons.exception;


import com.bingo.admin.commons.result.R;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R constraintViolationException(ConstraintViolationException ex) {
        return R.error("500", ex.getMessage());
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void accessDeniedException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ex.printStackTrace();
        request.getRequestDispatcher("/error/unauth").forward(request,response);
//        return R.error("500", ex.getMessage());
    }

//    @ExceptionHandler(value = { Exception.class })
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public R unknownException(Exception ex) {
//        ex.printStackTrace();
//        return R.error("500", ex.getMessage());
//    }
}
