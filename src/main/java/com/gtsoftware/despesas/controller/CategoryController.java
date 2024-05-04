package com.gtsoftware.despesas.controller;

import com.gtsoftware.despesas.model.Category;
import com.gtsoftware.despesas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();
        return  ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.create(category.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id, category.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
