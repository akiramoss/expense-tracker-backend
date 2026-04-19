package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.mapper.ExpenseMapper;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.model.ExpenseGroup;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.ExpenseGroupRepository;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseGroupRepository groupRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository, ExpenseGroupRepository groupRepository, ExpenseRepository repository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {


        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ExpenseGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Expense expense = Expense.builder()
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .createdAt(LocalDateTime.now())
                .user(user)
                .group(group)
                .build();

        return ExpenseMapper.toDTO(expenseRepository.save(expense));
    }

    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    public List<ExpenseResponseDTO> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    public List<ExpenseResponseDTO> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }
}
