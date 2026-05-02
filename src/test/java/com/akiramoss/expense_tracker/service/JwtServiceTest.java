package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.config.JwtService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldGenerateAndValidateToken() {

        String token = jwtService.generateToken("testuser");

        assertNotNull(token);
        assertTrue(jwtService.isTokenValid(token));
        assertEquals("testuser", jwtService.extractUsername(token));
    }
}
