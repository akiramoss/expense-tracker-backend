package com.akiramoss.expense_tracker.dto;

import lombok.Data;

@Data
public class ExpenseGroupRequestDTO {

    private String name;

    private String description;

    private Long ownerId;
}
