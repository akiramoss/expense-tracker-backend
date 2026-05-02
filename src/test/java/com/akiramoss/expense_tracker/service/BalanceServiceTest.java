package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.ExpenseShareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class BalanceServiceTest {

    private BalanceService balanceService;

    @BeforeEach
    void setUp() {
        balanceService = new BalanceService(
                mock(ExpenseRepository.class),
                mock(ExpenseShareRepository.class)
        );
    }

    @Test
    void shouldReturnBalancesWithoutErrors() {
        balanceService.getNetBalancesByGroup(1L);
    }
}
