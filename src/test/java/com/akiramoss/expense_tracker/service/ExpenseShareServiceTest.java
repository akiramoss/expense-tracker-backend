package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.SplitExpenseRequestDTO;
import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import com.akiramoss.expense_tracker.model.*;
import com.akiramoss.expense_tracker.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseShareServiceTest {

    private ExpenseRepository expenseRepository;
    private ExpenseShareRepository shareRepository;
    private UserRepository userRepository;
    private ExpenseGroupRepository groupRepository;

    private ExpenseShareService service;

    @BeforeEach
    void setUp() {
        expenseRepository = mock(ExpenseRepository.class);
        shareRepository = mock(ExpenseShareRepository.class);
        userRepository = mock(UserRepository.class);
        groupRepository = mock(ExpenseGroupRepository.class);

        service = new ExpenseShareService(
                expenseRepository,
                shareRepository,
                userRepository,
                groupRepository
        );
    }

    @Test
    void shouldSplitExpenseCorrectly() {

        SplitExpenseRequestDTO dto = new SplitExpenseRequestDTO();
        dto.setAmount(BigDecimal.valueOf(100));
        dto.setCategory(ExpenseCategory.FOOD);
        dto.setDate(LocalDate.now());
        dto.setPaymentMethod(PaymentMethod.CARD);
        dto.setPaidByUserId(1L);
        dto.setGroupId(1L);
        dto.setParticipantIds(List.of(1L, 2L, 3L));

        User payer = User.builder().id(1L).build();
        User user2 = User.builder().id(2L).build();
        User user3 = User.builder().id(3L).build();

        ExpenseGroup group = ExpenseGroup.builder().id(1L).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(payer));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.findById(3L)).thenReturn(Optional.of(user3));
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group));

        when(expenseRepository.save(any(Expense.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Expense result = service.splitExpense(dto);

        // THEN
        assertNotNull(result);
        verify(shareRepository, times(2)).save(any(ExpenseShare.class));
    }

    @Test
    void shouldFailIfLessThanTwoParticipants() {

        SplitExpenseRequestDTO dto = new SplitExpenseRequestDTO();
        dto.setParticipantIds(List.of(1L));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.splitExpense(dto)
        );

        assertTrue(ex.getMessage().contains("Split requires"));
    }
}
