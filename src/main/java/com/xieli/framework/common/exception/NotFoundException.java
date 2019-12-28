package com.xieli.framework.common.exception;

public class NotFoundException extends ZBException {
    public NotFoundException() {
        super("Resource Not Found", null);
    }
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}