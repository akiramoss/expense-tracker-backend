package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.LoginRequestDTO;
import com.akiramoss.expense_tracker.dto.RegisterRequestDTO;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.service.AuthService;
import com.akiramoss.expense_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterRequestDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
        return authService.login(dto);
    }
}
