package com.harshit.rav.exception;

public class EmailAlreadyExistsException extends Exception{
    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
