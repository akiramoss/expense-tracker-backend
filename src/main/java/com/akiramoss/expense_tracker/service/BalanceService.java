package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.BalanceDTO;
import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.model.ExpenseShare;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import com.akiramoss.expense_tracker.repository.ExpenseShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseShareRepository shareRepository;

    public List<BalanceDTO> getBalancesByGroup(Long groupId) {

        List<Expense> expenses = expenseRepository.findByGroupId(groupId);
        List<BalanceDTO> balances = new ArrayList<>();

        for (Expense expense : expenses) {

            Long payerId = expense.getUser().getId();

            List<ExpenseShare> shares = shareRepository.findByExpenseId(expense.getId());

            for (ExpenseShare share : shares) {

                balances.add(
                        BalanceDTO.builder()
                                .fromUserId(share.getUser().getId())
                                .toUserId(payerId)
                                .amount(share.getAmountOwed())
                                .build()
                );
            }
        }

        return balances;
    }
}