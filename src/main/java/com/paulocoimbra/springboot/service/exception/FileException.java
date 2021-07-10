package com.paulocoimbra.springboot.service.exception;

public class FileException extends RuntimeException {

    private static final long serialVersionUID = -970989948384721882L;

    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
