package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.ApiResponse;
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

    @GetMapping("/group/{groupId}/net")
    public ApiResponse<List<BalanceDTO>> getNetBalances(@PathVariable Long groupId) {

        return ApiResponse.<List<BalanceDTO>>builder()
                .status("success")
                .message("Balances retrieved successfully")
                .data(balanceService.getNetBalancesByGroup(groupId))
                .build();
    }
}

