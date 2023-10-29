package com.example.cnd.validator;

import com.example.cnd.common.enums.MessageError;
import com.example.cnd.common.utils.CommonUtil;
import com.example.cnd.exception.UnprocessableEntityException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Comparator;
import java.util.List;

@Data
public class BindingRequest {
    public void processValidateBindingResult(BindingResult br) {
        if (br != null && br.hasErrors()) {
            List<FieldError> errors = br.getFieldErrors()
                    .stream()
                    .sorted(Comparator.comparing(e -> ("." + e.getField()).split("\\.").length))
                    .toList();
            FieldError fieldError = errors.get(0);
            String code = fieldError.getCode();
            if (!CommonUtil.isNullOrWhiteSpace(code)) {
                throw new UnprocessableEntityException(MessageError.E_422.getMessageContent());
            }
        }
    }
}
