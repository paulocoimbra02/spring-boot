package com.paulocoimbra.springboot.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -970989948384721882L;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
