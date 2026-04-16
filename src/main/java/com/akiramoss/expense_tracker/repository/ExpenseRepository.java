package com.akiramoss.expense_tracker.repository;

import com.akiramoss.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByDate(LocalDate date);

    List<Expense> findByCategory(String category);

    List<Expense> findByUserId(Long userId);
}
