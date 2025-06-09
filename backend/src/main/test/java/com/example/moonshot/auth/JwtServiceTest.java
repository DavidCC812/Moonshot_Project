package com.example.moonshot.auth;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldGenerateAndValidateToken() {
        UserDetails userDetails = User.withUsername("user@example.com")
                .password("password")
                .roles("USER")
                .build();

        String token = jwtService.generateToken(java.util.UUID.randomUUID(), userDetails.getUsername());

        // Token should be valid and contain the correct email
        assertThat(jwtService.isTokenValid(token, userDetails)).isTrue();
        assertThat(jwtService.extractEmail(token)).isEqualTo("user@example.com");
    }

    @Test
    void shouldRejectExpiredToken() throws InterruptedException {
        UserDetails userDetails = User.withUsername("expired@example.com")
                .password("password")
                .roles("USER")
                .build();

        // Generate a token that expires in 10 milliseconds
        String token = jwtService.generateTokenWithExpiration(java.util.UUID.randomUUID(), userDetails.getUsername(), 10);

        // Wait for token to expire
        Thread.sleep(50);

        // Extracting email from expired token should throw
        assertThatThrownBy(() -> jwtService.extractEmail(token))
                .isInstanceOf(ExpiredJwtException.class);
    }
}