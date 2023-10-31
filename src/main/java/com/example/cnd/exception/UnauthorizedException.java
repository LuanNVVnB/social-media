package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnauthorizedException extends GeneralException {
    public UnauthorizedException(ErrorResponse error) {
        super(error);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
