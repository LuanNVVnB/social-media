package com.example.cnd.common.enums;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Getter;

@Getter
public enum MessageError {
    E_500(500, "Server Error"),
    E_422(422, "Format request error");

    private int messageId;
    private String messageContent;


    MessageError(int messageId, String messageContent) {
        this.messageId = messageId;
        this.messageContent = messageContent;
    }

    public static MessageError findById(int id) {
        for (MessageError item : MessageError.values()) {
            if (item.messageId == id) {
                return item;
            }
        }
        return null;
    }

    public static ErrorResponse findErrorById(MessageError item) {
        return new ErrorResponse(item.messageId, item.messageContent);
    }
}
