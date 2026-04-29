package com.akiramoss.expense_tracker.model;

import com.akiramoss.expense_tracker.enums.ExpenseCategory;
import com.akiramoss.expense_tracker.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    private ExpenseCategory category;

    private String description;

    private LocalDate date;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private PaymentMethod paymentMethod;

    @ManyToOne(optional = true)
    @JoinColumn(name = "group_id")
    private ExpenseGroup group;
}
