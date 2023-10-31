package com.example.cnd.exception;

import com.example.cnd.common.base.ErrorResponse;
import com.example.cnd.common.enums.MessageError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

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
