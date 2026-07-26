package com.peruch.fullstack_demo.controller;

import com.peruch.fullstack_demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        if("admin".equals(loginRequest.getUsername()) && "1234".equals(loginRequest.getPassword())){
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
