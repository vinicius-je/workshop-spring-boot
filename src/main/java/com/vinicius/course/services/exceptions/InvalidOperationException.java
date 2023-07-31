package com.vinicius.course.services.exceptions;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String msg){
        super("Invalid Operation: " + msg);
    }
}
