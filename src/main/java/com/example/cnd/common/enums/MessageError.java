package com.example.cnd.common.enums;

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


}
