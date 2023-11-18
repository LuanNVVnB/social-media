package com.example.cnd.common.enums;

import com.example.cnd.common.base.ErrorResponse;
import lombok.Getter;

/**
 * File: com.example.cnd.common.enums
 * Description:  <div style="font-weight: bold; color: #0073e6;"> MessageError </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;">LuanNVV</span>
 * Date: <span style="font-weight: bold; color: #ff5722;">11/18/2023</span>
 **/
@Getter
public enum MessageError {

    E_400(400, "Bad request"),
    E_401(401, "Unauthorized"),
    E_403(403, "Forbidden"),
    E_404(404, "Not Found"),
    E_422(422, "Format request error"),
    E_500(500, "Server Error");

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
