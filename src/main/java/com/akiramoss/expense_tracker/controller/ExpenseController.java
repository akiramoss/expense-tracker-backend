package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseService.createExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/category")
    public List<Expense> getByCategory(@RequestParam String category) {
        return expenseService.getExpensesByCategory(category);
    }
}
