package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadRequestException extends GeneralException {
    public BadRequestException(ErrorResponse error) {
        super(error);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}