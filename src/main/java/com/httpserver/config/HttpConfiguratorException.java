package com.httpserver.config;

public class HttpConfiguratorException extends RuntimeException {

    public HttpConfiguratorException() {
    }

    public HttpConfiguratorException(String message) {
        super(message);
    }

    public HttpConfiguratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpConfiguratorException(Throwable cause) {
        super(cause);
    }

}
