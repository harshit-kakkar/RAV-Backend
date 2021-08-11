package com.harshit.rav.exception;

public class AppointmentValidityException extends Exception{
    public AppointmentValidityException(String errorMessage) {
        super(errorMessage);
    }
}
