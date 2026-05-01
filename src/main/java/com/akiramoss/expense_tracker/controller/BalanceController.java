package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.BalanceDTO;
import com.akiramoss.expense_tracker.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/group/{groupId}")
    public List<BalanceDTO> getBalances(@PathVariable Long groupId) {
        return balanceService.getBalancesByGroup(groupId);
    }
}