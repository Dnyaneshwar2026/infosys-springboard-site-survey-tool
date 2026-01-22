package com.example.jwt_auth_demo.controller;

import com.example.jwt_auth_demo.model.AuthRequest;
import com.example.jwt_auth_demo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        // BASIC hardcoded authentication
        if ("admin".equals(request.getUsername())
                && "password".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername());

            return ResponseEntity.ok(
                    Map.of("token", token)
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid credentials"));
    }
}


