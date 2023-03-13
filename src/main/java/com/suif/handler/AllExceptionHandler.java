package com.suif.handler;

import com.suif.ex.UserException;
import com.suif.ex.InValidAccessException;
import com.suif.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(UserException.class)
    public Result doAccountException(Exception e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(InValidAccessException.class)
    public Result inValidAccessException(Exception e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception e){
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
