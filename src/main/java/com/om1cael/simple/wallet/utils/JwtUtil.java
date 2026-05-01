package com.om1cael.simple.wallet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("{jwt.token}")
    private String jwtToken;

    public String create(String email) {
        Algorithm algorithm = Algorithm.HMAC512(jwtToken);
        return JWT.create()
                .withIssuer("simplewallet")
                .withSubject(email)
                .sign(algorithm);
    }

    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC512(jwtToken);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("simplewallet")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
