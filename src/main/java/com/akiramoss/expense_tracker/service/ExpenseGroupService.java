package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseGroupRequestDTO;
import com.akiramoss.expense_tracker.model.ExpenseGroup;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.ExpenseGroupRepository;
import com.akiramoss.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseGroupService {

    private final ExpenseGroupRepository groupRepository;
    private final UserRepository userRepository;

    public ExpenseGroup createGroup(ExpenseGroupRequestDTO dto) {

        User owner = userRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseGroup group = ExpenseGroup.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .owner(owner)
                .build();

        return groupRepository.save(group);
    }

    public List<ExpenseGroup> getAllGroups() {
        return groupRepository.findAll();
    }
}
