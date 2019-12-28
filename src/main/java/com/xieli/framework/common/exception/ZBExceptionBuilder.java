package com.xieli.framework.common.exception;

import java.util.Arrays;

public class ZBExceptionBuilder {
    private int httpStatus = 400;
    private String errorCode;
    private Object[] args;
    private String message;
    private Throwable cause;

    public String toString() {
        return "ZBExceptionBuilder(status=" + this.httpStatus + ", errorCode=" + this.errorCode + ", args=" + Arrays.deepToString(this.args) + ")";
    }

    private void buildMessage() {
        if (message == null) {
            if (errorCode != null && args == null) {
                message = errorCode;
            } else if (errorCode != null && args != null) {
                message = errorCode + Arrays.deepToString(this.args);
            }
        }
    }

    public ZBException build() {
        if (errorCode == null && message == null && cause == null)
            throw new RuntimeException("Build ZBException failed, errorCode,message,cause can't be all empty");

        buildMessage();

        ZBException zbException = new ZBException(message, cause);
        zbException.setErrorCode(errorCode);
        zbException.setHttpStatus(httpStatus);
        zbException.setArgs(args);
        return zbException;
    }

    public ZBExceptionBuilder args(Object... args) {
        this.args = args;
        return this;
    }

    public ZBExceptionBuilder errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    /**
     * @param httpStatus default is 400
     * @return
     */
    public ZBExceptionBuilder httpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public ZBExceptionBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ZBExceptionBuilder cause(Throwable cause) {
        this.cause = cause;
        return this;
    }
}
