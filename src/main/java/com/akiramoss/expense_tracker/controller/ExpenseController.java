package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.ApiResponse;
import com.akiramoss.expense_tracker.dto.ExpenseRequestDTO;
import com.akiramoss.expense_tracker.dto.ExpenseResponseDTO;
import com.akiramoss.expense_tracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Expenses", description = "Operations related to expenses")
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "Create a new expense")
    @PostMapping
    public ApiResponse<ExpenseResponseDTO> createExpense(@Valid @RequestBody ExpenseRequestDTO dto) {

        ExpenseResponseDTO expense = expenseService.createExpense(dto);

        return ApiResponse.<ExpenseResponseDTO>builder()
                .status("success")
                .message("Expense created successfully")
                .data(expense)
                .build();
    }

    @Operation(summary = "Get all expenses")
    @GetMapping
    public List<ExpenseResponseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @Operation(summary = "Get expenses by category")
    @GetMapping("/category")
    public List<ExpenseResponseDTO> getByCategory(@RequestParam String category) {
        return expenseService.getExpensesByCategory(category);
    }
}
