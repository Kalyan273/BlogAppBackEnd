package com.blogApp.blogApp.exception;


import com.blogApp.blogApp.controller.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Response>resourseNotFound(ResourceNotFound ex){
        String message= ex.getMessage();
        Response response= new Response();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(message);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethosArgumentException(MethodArgumentNotValidException ex){
        Map<String, String> resp= new HashMap<>();
        List<ObjectError> allErrors = ex.getAllErrors();
        allErrors.stream().forEach(objectError -> {
            String fieldError=((FieldError)objectError).getField();
            String message= objectError.getDefaultMessage();
            resp.put(fieldError,message);
        });

        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
