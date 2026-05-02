package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.ExpenseType;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.model.User;
import com.akiramoss.expense_tracker.repository.ExpenseGroupRepository;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
    private ExpenseGroupRepository groupRepository;

    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseRepository = mock(ExpenseRepository.class);
        userRepository = mock(UserRepository.class);
        groupRepository = mock(ExpenseGroupRepository.class);

        expenseService = new ExpenseService(
                expenseRepository,
                userRepository,
                groupRepository
        );
    }

    @Test
    void shouldCreatePersonalExpense() {

        // GIVEN
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.valueOf(50));
        dto.setCategory(ExpenseCategory.FOOD);
        dto.setDate(LocalDate.now());
        dto.setUserId(1L);
        dto.setPaymentMethod(PaymentMethod.CASH);

        User user = User.builder().id(1L).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseRepository.save(any(Expense.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        var result = expenseService.createExpense(dto);

        // THEN
        assertNotNull(result);
        assertEquals(ExpenseCategory.FOOD, result.getCategory());
        assertEquals(ExpenseType.PERSONAL, result.getType());
    }

    @Test
    void shouldThrowIfUserNotFound() {

        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setUserId(999L);

        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> expenseService.createExpense(dto)
        );

        assertEquals("User not found", ex.getMessage());
    }
}

