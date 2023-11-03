package com.example.cnd.validator;

import com.example.cnd.common.enums.MessageError;
import com.example.cnd.common.utils.CommonUtil;
import com.example.cnd.exception.UnprocessableEntityException;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Comparator;
import java.util.List;

/**
 * File: com.example.cnd.validator
 * <p>
 * Description:
 * <div style="font-weight: bold; color: #0073e6;">
 * Binding Request and check validate data when client send to server
 * </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/3/2023</span>
 **/
@Data
@Service
@ControllerAdvice
public class BindingRequest {

    /**
     * Exception handler for handling validation errors in request data.
     * <p>
     * This method is responsible for processing validation errors, which occur when there are issues with the
     * data provided in a request. It retrieves the validation errors from the `BindingResult`, sorts them, and
     * identifies the first field error. If an error code is associated with the field error, it throws an
     * UnprocessableEntityException with the corresponding error message.
     *
     * @param br The BindingResult containing validation errors.
     */
    public void processValidateBindingResult(BindingResult br) {
        System.out.println(br);
        if (br != null && br.hasErrors()) {

            List<FieldError> errors = br.getFieldErrors()
                    .stream()
                    .sorted(Comparator.comparing(e -> ("." + e.getField()).split("\\.").length))
                    .toList();
            FieldError fieldError = errors.get(0);
            String code = fieldError.getCode();
            if (!CommonUtil.isNullOrWhiteSpace(code)) {
                throw new UnprocessableEntityException(MessageError.findErrorById(MessageError.E_422));
            }
        }
    }
}
