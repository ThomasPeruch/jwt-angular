package com.peruch.fullstack_demo.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.peruch.fullstack_demo.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");
            try {
                String username = JwtUtil.validateToken(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException e){
                logger.error(e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}