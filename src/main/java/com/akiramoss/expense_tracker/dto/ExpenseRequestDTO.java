package com.akiramoss.expense_tracker.dto;

import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

// Entrada del usuario
@Data
public class ExpenseRequestDTO {

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull
    private ExpenseCategory category;

    @Size(max = 255)
    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long userId;

    private Long groupId;

    @NotNull
    private PaymentMethod paymentMethod;
}
