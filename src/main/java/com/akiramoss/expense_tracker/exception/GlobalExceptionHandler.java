package com.akiramoss.expense_tracker.exception;

import com.akiramoss.expense_tracker.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.badRequest().body(
                ApiResponse.<String>builder()
                        .status("error")
                        .message(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(
                ApiResponse.<String>builder()
                        .status("error")
                        .message(errorMessage)
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {

        return ResponseEntity.internalServerError().body(
                ApiResponse.<String>builder()
                        .status("error")
                        .message("Unexpected error occurred")
                        .build()
        );
    }
}
