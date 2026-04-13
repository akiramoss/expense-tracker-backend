package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.mapper.ExpenseMapper;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseResponseDTO createExpense(ExpenseRequestDTO dto) {

        Expense expense = Expense.builder()
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .createdAt(LocalDateTime.now())
                .build();

        Expense saved = expenseRepository.save(expense);

        return ExpenseMapper.toDTO(saved);
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
}
