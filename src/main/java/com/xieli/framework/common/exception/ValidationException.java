package com.xieli.framework.common.exception;

import com.xieli.framework.common.entity.Message;

import java.util.List;

public class ValidationException extends ZBException {
    private List<Message> errorList;

    protected ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(List<Message> errorList) {
        super(null, null);
        this.errorList = errorList;
    }

    public List<Message> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Message> errorList) {
        this.errorList = errorList;
    }
}
