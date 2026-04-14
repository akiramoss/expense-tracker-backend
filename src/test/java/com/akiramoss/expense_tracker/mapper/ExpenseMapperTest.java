package com.akiramoss.expense_tracker.mapper;

import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.model.Expense;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1. Validación para no perder datos
 * 2. Que el Mapper funcione correctamente
 */
class ExpenseMapperTest {

    @Test
    void shouldMapExpenseToDTO() {

        Expense expense = Expense.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(50))
                .category("food")
                .description("pizza")
                .date(LocalDate.now())
                .createdAt(LocalDateTime.now())
                .build();

        ExpenseResponseDTO dto = ExpenseMapper.toDTO(expense);

        assertEquals(expense.getId(), dto.getId());
        assertEquals(expense.getAmount(), dto.getAmount());
        assertEquals(expense.getCategory(), dto.getCategory());
    }
}