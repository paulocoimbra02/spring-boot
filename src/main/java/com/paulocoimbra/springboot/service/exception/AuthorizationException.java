package com.paulocoimbra.springboot.service.exception;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = -970989948384721882L;

    public AuthorizationException(String msg) {
        super(msg);
    }

    public AuthorizationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
