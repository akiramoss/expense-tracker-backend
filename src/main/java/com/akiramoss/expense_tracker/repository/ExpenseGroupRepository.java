package com.akiramoss.expense_tracker.repository;

import com.akiramoss.expense_tracker.model.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup, Long> {
}
