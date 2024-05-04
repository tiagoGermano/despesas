package com.gtsoftware.despesas.controller;

import com.gtsoftware.despesas.controller.dto.ExpenseDTO;
import com.gtsoftware.despesas.model.Expense;
import com.gtsoftware.despesas.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class PaymentController {
    private final ExpenseService expenseService;

    @Autowired
    public PaymentController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(path = "/api/expenses/payed-expense")
    public ResponseEntity<Expense> createExpenseWithPayment(@RequestBody ExpenseDTO expenseDTO){
        try {
            Expense expense = expenseService.createPayedExpense(expenseDTO);
            return  ResponseEntity.ok(expense);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
