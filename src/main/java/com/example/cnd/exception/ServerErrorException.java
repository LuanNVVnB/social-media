package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import com.example.cnd.common.enums.MessageError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * File: com.example.cnd.exception
 * Description:  <div style="font-weight: bold; color: #0073e6;"> ServerErrorException </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerErrorException extends GeneralException {
    public ServerErrorException(ErrorResponse error) {
        super(error);
    }

    public ServerErrorException() {
        super(MessageError.findErrorById(MessageError.E_500));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
