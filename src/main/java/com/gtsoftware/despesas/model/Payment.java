package com.gtsoftware.despesas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @Column(nullable = false)
    private Double amountPaid;

    @Column(nullable = false)
    private Calendar payDay;

    private Double interest;

    private String receiptUrl;
}
