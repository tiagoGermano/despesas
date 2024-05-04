package com.gtsoftware.despesas.service;

import com.gtsoftware.despesas.controller.dto.ExpenseDTO;
import com.gtsoftware.despesas.model.Category;
import com.gtsoftware.despesas.model.Expense;
import com.gtsoftware.despesas.model.Payment;
import com.gtsoftware.despesas.repository.ExpenseRepository;
import com.gtsoftware.despesas.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    @Autowired
    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense create(ExpenseDTO expenseDTO) throws ParseException {
            Expense expense = toExpense(expenseDTO);
            return repository.save(expense);
    }

    public Expense findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Expense> findAll() {
        return repository.findAll();
    }

    public Expense update(Long id, ExpenseDTO expenseDTO) throws ParseException {
        Expense toUpdate = repository.findById(id).orElseThrow();
        Calendar expenseDue = DateUtils.strToCalendar(expenseDTO.getDue());

        toUpdate.setSeller(expenseDTO.getSeller());
        toUpdate.setAmount(expenseDTO.getAmount());
        toUpdate.setDescription(expenseDTO.getDescription());
        toUpdate.setDue(expenseDue);
        toUpdate.getCategory().setId(expenseDTO.getCategoryId());
        return repository.save(toUpdate);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Expense createPayedExpense(ExpenseDTO expenseDTO) throws ParseException {
        Expense expense = toExpense(expenseDTO);
        Calendar paymentDate = DateUtils.strToCalendar(expenseDTO.getDue());
        Payment payment = new Payment();
        payment.setPayDay(Calendar.getInstance());
        payment.setInterest(0d);
        payment.setAmountPaid(expenseDTO.getAmount());
        payment.setReceiptUrl("some img repository");
        expense.setPayment(payment);
        return repository.save(expense);
    }

    private Expense toExpense(ExpenseDTO expenseDTO) throws ParseException {
        Calendar expenseDue = DateUtils.strToCalendar(expenseDTO.getDue());

        return Expense.builder()
                .seller(expenseDTO.getSeller())
                .description(expenseDTO.getDescription())
                .category(new Category(expenseDTO.getCategoryId()))
                .amount(expenseDTO.getAmount())
                .due(expenseDue)
                .build();
    }
}
