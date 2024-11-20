package com.tanvir.easyMart.exceptions;

import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentNotValid (MethodArgumentNotValidException exception) {
        Map<String,String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            response.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException error){
        String message = error.getMessage()+ " \uD83D\uDE25";
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> myAPIException(APIException error){
        String message = error.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

}
