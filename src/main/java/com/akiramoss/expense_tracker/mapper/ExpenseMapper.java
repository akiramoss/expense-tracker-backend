package com.akiramoss.expense_tracker.mapper;

import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.model.Expense;

public final class ExpenseMapper {

    private ExpenseMapper() {
    }

    public static ExpenseResponseDTO toDTO(Expense expense) {

        if (expense == null) {
            return null;
        }

        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .description(expense.getDescription())
                .date(expense.getDate())
                .createdAt(expense.getCreatedAt())
                .userId(expense.getUser().getId())
                .groupId(expense.getGroup() != null ? expense.getGroup().getId() : null)
                .type(expense.getType())
                .build();
    }
}
