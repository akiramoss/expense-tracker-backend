package com.akiramoss.expense_tracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BalanceDTO {

    private Long fromUserId;

    private Long toUserId;

    private BigDecimal amount;
}
