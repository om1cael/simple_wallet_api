package com.om1cael.simple.wallet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtUtil {
    @Value("${jwt.token}")
    private String jwtToken;

    String create() {
        Algorithm algorithm = Algorithm.HMAC512(jwtToken);
        return JWT.create()
                .withIssuer("simplewallet")
                .withExpiresAt(Instant.now().plus(15, ChronoUnit.MINUTES))
                .sign(algorithm);
    }
}
