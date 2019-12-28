package com.xieli.framework.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.text.MessageFormat;

@Getter
@Setter
public class Message implements Serializable {
    private String code;
    private String message;

    public static Message message(String code, String message) {
        Message apiMessage = new Message();
        apiMessage.setCode(code);
        apiMessage.setMessage(message);
        return apiMessage;
    }

    public static Message message(String code, String message, Object ...args) {
        Message apiMessage = new Message();
        apiMessage.setCode(code);
        if (message != null) {
            String errorMessage = MessageFormat.format(message, args);
            apiMessage.setMessage(errorMessage);
        }
        return apiMessage;
    }
}