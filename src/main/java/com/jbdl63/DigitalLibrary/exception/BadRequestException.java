package com.jbdl63.DigitalLibrary.exception;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
