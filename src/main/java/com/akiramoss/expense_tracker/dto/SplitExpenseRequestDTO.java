package com.akiramoss.expense_tracker.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SplitExpenseRequestDTO {

    private BigDecimal amount;

    private String category;

    private String description;

    private LocalDate date;

    private Long paidByUserId;

    private Long groupId;

    private List<Long> participantIds;
}
