package com.peruch.fullstack_demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "tsEwVU-O9";

    public static String generateToken(String username){

        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .sign(algorithm);

    }

    public static String validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return decodedJWT.getSubject();
    }
}
