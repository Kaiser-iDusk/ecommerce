package com.ecommerce.ecom.common;

import com.ecommerce.ecom.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static ResponseEntity<Map<String, String>> buildException(Exception ex, HttpStatus status){
        Map<String, String> errors= new HashMap<>();
        errors.put("status", status.toString());
        errors.put("message", ex.getMessage());
        return ResponseEntity.status(status).body(errors);
    }

    public static User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null) return null;
        return (User) auth.getPrincipal();
    }
}
