package com.akiramoss.expense_tracker.dto;

import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SplitExpenseRequestDTO {

    @NotNull
    private BigDecimal amount;

    @NotNull
    private ExpenseCategory category;

    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long paidByUserId;

    @NotNull
    private Long groupId;

    @NotEmpty
    private List<Long> participantIds;
}
