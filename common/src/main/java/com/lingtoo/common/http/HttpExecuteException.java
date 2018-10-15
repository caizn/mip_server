package com.lingtoo.common.http;

public class HttpExecuteException extends Exception {
    public HttpExecuteException() {
        super();
    }

    public HttpExecuteException(String message) {
        super(message);
    }

    public HttpExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpExecuteException(Throwable cause) {
        super(cause);
    }
}