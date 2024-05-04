package com.gtsoftware.despesas.controller.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    private double amountPaid;
    private String payDay;
    private double interest;
    private String receiptUrl;
}
