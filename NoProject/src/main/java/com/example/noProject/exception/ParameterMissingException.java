package com.example.noProject.exception;

public class ParameterMissingException extends ParameterException{
    public ParameterMissingException(String message){
        super(message);
    }

    public ParameterMissingException() {
        super();
    }
}
