package com.gtsoftware.despesas.controller;

import com.gtsoftware.despesas.ResponseError;
import com.gtsoftware.despesas.controller.dto.ExpenseDTO;
import com.gtsoftware.despesas.model.Category;
import com.gtsoftware.despesas.model.Expense;
import com.gtsoftware.despesas.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping()
    public ResponseEntity<List<Expense>> findAll() {
        List<Expense> expenses = expenseService.findAll();
        return  ResponseEntity.ok(expenses);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Expense> find(@PathVariable long id) {
        Expense expense = expenseService.findById(id);
        return  ResponseEntity.ok(expense);
    }

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody ExpenseDTO expenseDTO){
        try {
            Expense expense = expenseService.create(expenseDTO);
            return  ResponseEntity.ok(expense);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Expense> update(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO){
        try {
            Expense expense = expenseService.update(id, expenseDTO);
            return  ResponseEntity.ok(expense);

        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
