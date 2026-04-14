package com.akiramoss.expense_tracker.dto;

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

    private String category;

    private String description;

    private LocalDate date;

    private LocalDateTime createdAt;
}
