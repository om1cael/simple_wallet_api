package com.om1cael.simple.wallet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("{jwt.token}")
    private String jwtToken;

    String create() {
        Algorithm algorithm = Algorithm.HMAC512(jwtToken);
        return JWT.create()
                .withIssuer("simplewallet")
                .sign(algorithm);
    }

    boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(jwtToken);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("simplewallet")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
