package com.akiramoss.expense_tracker.dto;

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

    @NotBlank(message = "Category is required")
    private String category;

    @Size(max = 255, message = "Description too long")
    private String description;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private Long userId;

    private Long groupId;

    private String paymentMethod;
}
