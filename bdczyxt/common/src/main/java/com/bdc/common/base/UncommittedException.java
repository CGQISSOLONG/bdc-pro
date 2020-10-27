package com.bdc.common.base;

public class UncommittedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UncommittedException(String message) {
        super(message);
    }
}
