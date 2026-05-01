package com.akiramoss.expense_tracker.mapper;

import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.ExpenseType;
import com.akiramoss.expense_tracker.model.Expense;

// Convertimos Entity en DTO
// Adapter patter
public final class ExpenseMapper {

    private ExpenseMapper() {

    }

    public static ExpenseResponseDTO toDTO(Expense expense) {

        if (expense == null) {
            return null;
        }

        // Transformamos la entidad "Expense" en un DTO con builder()
        return ExpenseResponseDTO.builder()
                .id(expense.getId())
                .amount(expense.getAmount())
                .category(ExpenseCategory.valueOf(expense.getCategory().name()))
                .description(expense.getDescription())
                .date(expense.getDate())
                .createdAt(expense.getCreatedAt())
                .userId(expense.getUser().getId())
                .groupId(expense.getGroup() != null ? expense.getGroup().getId() : null)
                .type(ExpenseType.valueOf(expense.getType().name()))
                .build();
    }
}
