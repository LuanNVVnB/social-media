package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnprocessableEntityException extends GeneralException {
    public UnprocessableEntityException(ErrorResponse err) {
        super(err);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

}
