package com.gtsoftware.despesas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    public Category() {}

    public Category(Integer id){
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

}