package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.ApiResponse;
import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.dto.SplitExpenseRequestDTO;
import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.mapper.ExpenseMapper;
import com.akiramoss.expense_tracker.service.ExpenseService;
import com.akiramoss.expense_tracker.service.ExpenseShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
@Tag(name = "Expenses", description = "Operations related to expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseShareService expenseShareService;

    @Operation(summary = "Create a new expense")
    @PostMapping
    public ApiResponse<ExpenseResponseDTO> createExpense(
            @Valid @RequestBody ExpenseRequestDTO dto) {

        return ApiResponse.<ExpenseResponseDTO>builder()
                .status("success")
                .message("Expense created successfully")
                .data(expenseService.createExpense(dto))
                .build();
    }

    @Operation(summary = "Get all expenses")
    @GetMapping
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @Operation(summary = "Get expenses by category")
    @GetMapping("/category")
    public List<ExpenseResponseDTO> getByCategory(
            @RequestParam ExpenseCategory category) {

        return expenseService.getExpensesByCategory(category);
    }

    @Operation(summary = "Get expenses by user")
    @GetMapping("/user/{userId}")
    public List<ExpenseResponseDTO> getByUser(
            @PathVariable Long userId) {

        return expenseService.getExpensesByUser(userId);
    }

    @Operation(summary = "Get expenses by group")
    @GetMapping("/group/{groupId}")
    public List<ExpenseResponseDTO> getByGroup(
            @PathVariable Long groupId) {

        return expenseService.getExpensesByGroup(groupId);
    }

    @Operation(summary = "Create shared split expense")
    @PostMapping("/split")
    public ApiResponse<ExpenseResponseDTO> splitExpense(
            @Valid @RequestBody SplitExpenseRequestDTO dto) {

        ExpenseResponseDTO result =
                ExpenseMapper.toDTO(expenseShareService.splitExpense(dto));

        return ApiResponse.<ExpenseResponseDTO>builder()
                .status("success")
                .message("Split expense created")
                .data(result)
                .build();
    }
}
