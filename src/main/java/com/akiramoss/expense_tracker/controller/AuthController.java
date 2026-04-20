package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.*;
import com.akiramoss.expense_tracker.service.AuthService;
import com.akiramoss.expense_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<UserResponseDTO> register(@RequestBody RegisterRequestDTO dto) {

        UserResponseDTO user = userService.register(dto);

        return ApiResponse.<UserResponseDTO>builder()
                .status("success")
                .message("User created successfully")
                .data(user)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequestDTO dto) {

        String token = authService.login(dto);

        return ApiResponse.<String>builder()
                .status("success")
                .message("Login successful")
                .data(token)
                .build();
    }
}
