package com.example.cnd.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Data
@ControllerAdvice
public class GeneralException extends RuntimeException {
    private Integer code;
    private String message;

    public GeneralException(String message) {
        super(message);
    }

}
