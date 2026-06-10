package com.ecommerce.ecom.common;

import com.ecommerce.ecom.products.exceptions.ProductNotFoundException;
import com.ecommerce.ecom.users.exceptions.PasswordMismatchException;
import com.ecommerce.ecom.users.exceptions.UserAlreadyExistsException;
import com.ecommerce.ecom.users.exceptions.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(
            ProductNotFoundException ex
    ){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", HttpStatus.NOT_FOUND.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
    ){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> {
                            errors.put(error.getField(), error.getDefaultMessage());
                        }
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExists(
            UserAlreadyExistsException ex
    ){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", HttpStatus.CONFLICT.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserDoesNotExists(
            UserDoesNotExistsException ex
    ){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", HttpStatus.NOT_FOUND.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Map<String, String>> handlePasswordMismatch(
            PasswordMismatchException ex
    ){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", HttpStatus.UNAUTHORIZED.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
}
