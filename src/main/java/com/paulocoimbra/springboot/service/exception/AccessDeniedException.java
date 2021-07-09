package com.paulocoimbra.springboot.service.exception;

public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = -970989948384721882L;

    public AccessDeniedException(String msg) {
        super(msg);
    }

    public AccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
