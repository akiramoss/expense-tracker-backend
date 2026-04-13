package com.akiramoss.expense_tracker.mapper;

import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.model.Expense;

// Convertimos Entity en DTO
public class ExpenseMapper {

    public static ExpenseResponseDTO toDTO(Expense expense) {
        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .description(expense.getDescription())
                .date(expense.getDate())
                .createdAt(expense.getCreatedAt())
                .build();
    }
}
