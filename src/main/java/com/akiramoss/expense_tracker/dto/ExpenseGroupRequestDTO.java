package com.akiramoss.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseGroupRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Long ownerId;
}