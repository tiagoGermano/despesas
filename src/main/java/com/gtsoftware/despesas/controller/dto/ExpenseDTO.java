package com.gtsoftware.despesas.controller.dto;

import lombok.Data;

@Data
public class ExpenseDTO {
    private String seller;
    private String description;
    private Double amount;
    private String due;
    private int categoryId;

}
