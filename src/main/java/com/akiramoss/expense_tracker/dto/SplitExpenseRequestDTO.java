package com.akiramoss.expense_tracker.dto;

import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SplitExpenseRequestDTO {

    private BigDecimal amount;

    private ExpenseCategory category;

    private String description;

    private LocalDate date;

    private PaymentMethod paymentMethod;

    private Long paidByUserId;

    private Long groupId;

    private List<Long> participantIds;
}
