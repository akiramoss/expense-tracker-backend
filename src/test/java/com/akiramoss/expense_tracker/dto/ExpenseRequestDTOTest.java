package com.akiramoss.expense_tracker.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseRequestDTOTest {

    private Validator validator;

    // Ejecución previa a cada test
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenAmountIsZero() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.ZERO);
        dto.setCategory("food");
        dto.setDate(LocalDate.now());

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenCategoryIsBlank() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.TEN);
        dto.setCategory("");
        dto.setDate(LocalDate.now());

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldPassWithValidData() {
        ExpenseRequestDTO dto = new ExpenseRequestDTO();
        dto.setAmount(BigDecimal.TEN);
        dto.setCategory("food");
        dto.setDate(LocalDate.now());

        Set<ConstraintViolation<ExpenseRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}
