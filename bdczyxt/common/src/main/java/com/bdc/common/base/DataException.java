package com.bdc.common.base;

public class DataException extends RuntimeException{

    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }
}