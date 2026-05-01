package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.ExpenseType;
import com.akiramoss.expense_tracker.mapper.ExpenseMapper;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.model.ExpenseGroup;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.ExpenseGroupRepository;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseGroupRepository groupRepository;

    @Transactional
    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {

        User user = findUserById(dto.getUserId());
        ExpenseGroup group = findGroupIfPresent(dto.getGroupId());

        ExpenseType type = (group == null)
                ? ExpenseType.PERSONAL
                : ExpenseType.GROUP;

        Expense expense = Expense.builder()
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .paymentMethod(dto.getPaymentMethod())
                .type(type)
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

    public List<ExpenseResponseDTO> getExpensesByCategory(ExpenseCategory category) {
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

    public List<ExpenseResponseDTO> getExpensesByGroup(Long groupId) {
        return expenseRepository.findByGroupId(groupId)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private ExpenseGroup findGroupIfPresent(Long groupId) {

        if (groupId == null) {
            return null;
        }

        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }
}
