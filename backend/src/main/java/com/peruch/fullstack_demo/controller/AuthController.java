package com.peruch.fullstack_demo.controller;

import com.peruch.fullstack_demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        if("admin".equals(loginRequest.getUsername()) && "1234".equals(loginRequest.getPassword())){
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping()
    public ResponseEntity<?> verifyToken(){
        if (SecurityContextHolder.getContext().getAuthentication() != null){
            boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
            if(isAuthenticated) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
