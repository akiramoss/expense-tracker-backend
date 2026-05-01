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

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;

    private ExpenseCategory expenseCategory;

    @Size(max = 255, message = "Description too long")
    private String description;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private Long userId;

    private Long groupId;

    private PaymentMethod paymentMethod;
}
