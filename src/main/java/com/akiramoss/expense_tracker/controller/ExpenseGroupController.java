package com.akiramoss.expense_tracker.controller;

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
    public ExpenseGroup create(@RequestBody ExpenseGroupRequestDTO dto) {
        return service.createGroup(dto);
    }

    @GetMapping
    public List<ExpenseGroup> getAll() {
        return service.getAllGroups();
    }
}
