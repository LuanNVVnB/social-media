package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * File: com.example.cnd.exception
 * Description:  <div style="font-weight: bold; color: #0073e6;"> ForbiddenException </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ForbiddenException extends GeneralException {
    public ForbiddenException(ErrorResponse error) {
        super(error);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}