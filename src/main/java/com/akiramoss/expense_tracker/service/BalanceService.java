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

    public List<BalanceDTO> getNetBalancesByGroup(Long groupId) {

        List<BalanceDTO> rawBalances = getBalancesByGroup(groupId);

        Map<String, BigDecimal> aggregated = new HashMap<>();

        for (BalanceDTO b : rawBalances) {

            String key = b.getFromUserId() + "-" + b.getToUserId();

            aggregated.put(
                    key,
                    aggregated.getOrDefault(key, BigDecimal.ZERO)
                            .add(b.getAmount())
            );
        }

        List<BalanceDTO> result = new ArrayList<>();

        for (Map.Entry<String, BigDecimal> entry : aggregated.entrySet()) {

            String[] parts = entry.getKey().split("-");

            result.add(
                    BalanceDTO.builder()
                            .fromUserId(Long.parseLong(parts[0]))
                            .toUserId(Long.parseLong(parts[1]))
                            .amount(entry.getValue())
                            .build()
            );
        }

        return result;
    }
}
