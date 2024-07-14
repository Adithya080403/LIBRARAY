package com.jbdl63.DigitalLibrary.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.*;

@ControllerAdvice
public class authorGlobalException
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> exceptHandler(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest)
    {
        Map<String,String > mp=new LinkedHashMap<>();
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldname=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            mp.put(fieldname,message);

        });
        return new  ResponseEntity<>(mp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> updateExceptHandler(BadRequestException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
