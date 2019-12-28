package com.xieli.framework.common.exception;

public class ZBException extends RuntimeException {
    private int httpStatus = 400;
    private String errorCode;
    private Object[] args;


    public ZBException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ZBExceptionBuilder builder() {
        return new ZBExceptionBuilder();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}