package com.example.cnd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.GeneralSecurityException;

@ControllerAdvice
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends GeneralException{

    public UnprocessableEntityException(String message) {
        super(message);
    }

}
