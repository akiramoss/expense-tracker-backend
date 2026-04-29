package com.akiramoss.expense_tracker.controller;

import com.akiramoss.expense_tracker.dto.ApiResponse;
import com.akiramoss.expense_tracker.dto.ExpenseGroupRequestDTO;
import com.akiramoss.expense_tracker.model.ExpenseGroup;
import com.akiramoss.expense_tracker.service.ExpenseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class ExpenseGroupController {

    private final ExpenseGroupService service;

    @PostMapping
    public ApiResponse<ExpenseGroup> create(@RequestBody ExpenseGroupRequestDTO dto) {
        return ApiResponse.<ExpenseGroup>builder()
                .status("success")
                .message("Group created")
                .data(service.createGroup(dto))
                .build();
    }

    @GetMapping
    public List<ExpenseGroup> getAll() {
        return service.getAllGroups();
    }
}
