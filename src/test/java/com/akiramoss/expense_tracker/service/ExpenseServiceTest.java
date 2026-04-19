package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.model.ExpenseGroup;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.ExpenseGroupRepository;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private final ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ExpenseGroupRepository groupRepository = mock(ExpenseGroupRepository.class);

    private final ExpenseService expenseService =
            new ExpenseService(expenseRepository, userRepository, groupRepository);

    @Test
    void shouldCreateExpenseSuccessfully() {

        // DTO input
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.valueOf(20));
        dto.setCategory("food");
        dto.setDescription("pizza");
        dto.setDate(LocalDate.now());
        dto.setUserId(1L);
        dto.setGroupId(1L);
        dto.setPaymentMethod("card");

        // Fake User
        User user = User.builder()
                .id(1L)
                .username("akio")
                .build();

        // Fake Group
        ExpenseGroup group = ExpenseGroup.builder()
                .id(1L)
                .name("Ibiza")
                .build();

        // Fake Saved Expense
        Expense savedExpense = Expense.builder()
                .id(1L)
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .paymentMethod(dto.getPaymentMethod())
                .user(user)
                .group(group)
                .build();

        // Mock behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));
        when(expenseRepository.save(any(Expense.class))).thenReturn(savedExpense);

        // Execute
        var result = expenseService.createExpense(dto);

        // Assertions
        assertNotNull(result);
        assertEquals("food", result.getCategory());
        assertEquals(BigDecimal.valueOf(20), result.getAmount());

        verify(userRepository, times(1)).findById(1L);
        verify(groupRepository, times(1)).findById(1L);
        verify(expenseRepository, times(1)).save(any(Expense.class));
    }
}
