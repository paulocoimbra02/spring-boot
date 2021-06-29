package com.paulocoimbra.springboot.service.exception;

public class DataIntegrityException extends RuntimeException {

    private static final long serialVersionUID = -970989948384721882L;

    public DataIntegrityException(String msg) {
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
