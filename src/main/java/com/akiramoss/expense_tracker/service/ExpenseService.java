package com.akiramoss.expense_tracker.service;

import com.akiramoss.expense_tracker.model.Expense;
import com.akiramoss.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {

        if (expense.getAmount().doubleValue() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        expense.setCreatedAt(LocalDateTime.now());

        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }


}
