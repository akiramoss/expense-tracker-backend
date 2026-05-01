package com.akiramoss.expense_tracker.dto;

import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.ExpenseType;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Salida de la API
@Data
@Builder
public class ExpenseResponseDTO {

    private Long id;

    private BigDecimal amount;

    private ExpenseCategory category;

    private ExpenseType type;

    private String description;

    private LocalDate date;

    private LocalDateTime createdAt;

    private Long userId;

    private Long groupId;

    private PaymentMethod paymentMethod;
}
