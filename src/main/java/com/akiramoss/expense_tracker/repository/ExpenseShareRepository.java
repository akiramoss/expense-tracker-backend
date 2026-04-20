package com.akiramoss.expense_tracker.repository;

import com.akiramoss.expense_tracker.model.ExpenseShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, Long> {

    List<ExpenseShare> findByExpenseId(Long expenseId);

    List<ExpenseShare> findByUserId(Long userId);
}
