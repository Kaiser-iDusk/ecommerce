package com.ecommerce.ecom.common;

import com.ecommerce.ecom.categories.exceptions.InvalidCategoryException;
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
        return Utils.buildException(ex, HttpStatus.NOT_FOUND);
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
        return Utils.buildException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserDoesNotExists(
            UserDoesNotExistsException ex
    ){
        return Utils.buildException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Map<String, String>> handlePasswordMismatch(
            PasswordMismatchException ex
    ){
        return Utils.buildException(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategory(
            InvalidCategoryException ex
    ){
        return Utils.buildException(ex, HttpStatus.NOT_FOUND);
    }
}
