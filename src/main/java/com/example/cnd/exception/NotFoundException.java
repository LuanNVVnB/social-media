package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends GeneralException {
    public NotFoundException(ErrorResponse error) {
        super(error);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}