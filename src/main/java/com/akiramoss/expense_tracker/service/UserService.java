package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.RegisterRequestDTO;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(RegisterRequestDTO dto) {

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }
}
