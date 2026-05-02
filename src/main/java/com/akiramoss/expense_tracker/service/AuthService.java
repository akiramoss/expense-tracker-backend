package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.config.JwtService;
import com.akiramoss.expense_tracker.dto.LoginRequestDTO;
import com.akiramoss.expense_tracker.exception.InvalidPasswordException;
import com.akiramoss.expense_tracker.exception.UserNotFoundException;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(LoginRequestDTO dto) {

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
