package com.vinicius.course.resources.exceptions;

import com.vinicius.course.services.exceptions.DatabaseException;
import com.vinicius.course.services.exceptions.InvalidOperationException;
import com.vinicius.course.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<StandardError> invalidOperation(InvalidOperationException e, HttpServletRequest request){
        String error = "Invalid Operation";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public  ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String error = "Constrain Violation Exception";

        String message = "";
        for(ConstraintViolation<?> err : e.getConstraintViolations()){
            message += err.getPropertyPath() + ": " + err.getConstraintDescriptor().getMessageTemplate() + "\n";
        }

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
