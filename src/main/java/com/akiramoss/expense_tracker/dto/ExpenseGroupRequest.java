package com.akiramoss.expense_tracker.dto;

import lombok.Data;

@Data
public class ExpenseGroupRequest {

    private String name;

    private String description;

    private Long ownerId;
}
