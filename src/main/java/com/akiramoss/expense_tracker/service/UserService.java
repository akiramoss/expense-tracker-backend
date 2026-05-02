package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.RegisterRequestDTO;
import com.akiramoss.expense_tracker.dto.UserResponseDTO;
import com.akiramoss.expense_tracker.exception.EmailAlreadyExistsException;
import com.akiramoss.expense_tracker.exception.UsernameAlreadyExistsException;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(RegisterRequestDTO dto) {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        User saved = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(saved.getId())
                .username(saved.getUsername())
                .email(saved.getEmail())
                .build();
    }
}
