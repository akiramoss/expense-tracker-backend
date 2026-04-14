package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private final ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
    private final ExpenseService expenseService = new ExpenseService(expenseRepository);

    @Test
    void shouldCreateExpenseSuccessfully() {

        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.valueOf(20));
        dto.setCategory("food");
        dto.setDescription("pizza");
        dto.setDate(LocalDate.now());

        Expense savedExpense = Expense.builder()
                .id(1L)
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .build();

        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);

        var result = expenseService.createExpense(dto);

        assertNotNull(result);
        assertEquals("food", result.getCategory());
    }
}