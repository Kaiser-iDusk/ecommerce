package com.ecommerce.ecom.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static ResponseEntity<Map<String, String>> buildException(Exception ex, HttpStatus status){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", status.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(status).body(errors);
    }
}
