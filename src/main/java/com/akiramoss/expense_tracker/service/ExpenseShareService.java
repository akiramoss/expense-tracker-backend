package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.dto.SplitExpenseRequestDTO;
import com.akiramoss.expense_tracker.model.*;
import com.akiramoss.expense_tracker.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseShareService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseShareRepository shareRepository;
    private final UserRepository userRepository;
    private final ExpenseGroupRepository groupRepository;

    public Expense splitExpense(SplitExpenseRequestDTO dto) {

        User payer = userRepository.findById(dto.getPaidByUserId())
                .orElseThrow(() -> new RuntimeException("Payer not found"));

        ExpenseGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Expense expense = Expense.builder()
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate())
                .createdAt(LocalDateTime.now())
                .user(payer)
                .group(group)
                .build();

        Expense savedExpense = expenseRepository.save(expense);

        BigDecimal eachShare = dto.getAmount()
                .divide(BigDecimal.valueOf(dto.getParticipantIds().size()), 2, RoundingMode.HALF_UP);

        for (Long userId : dto.getParticipantIds()) {

            if (userId.equals(dto.getPaidByUserId())) {
                continue;
            }

            User participant = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            ExpenseShare share = ExpenseShare.builder()
                    .expense(savedExpense)
                    .user(participant)
                    .amountOwed(eachShare)
                    .settled(false)
                    .build();

            shareRepository.save(share);
        }

        return savedExpense;
    }
}
