package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class GeneralException extends RuntimeException {
    private HttpStatus httpStatus;
    private ErrorResponse error;

    public GeneralException() {
        super();
    }

    public GeneralException(ErrorResponse error) {
        super(error.getMessage());
        this.error = error;
    }
}
