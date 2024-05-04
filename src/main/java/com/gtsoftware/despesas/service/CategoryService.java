package com.gtsoftware.despesas.service;

import com.gtsoftware.despesas.model.Category;
import com.gtsoftware.despesas.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.repository = categoryRepository;
    }

    /***
     * Throw NoSuchElementException when Category not found
     *
    */
    public Category findById(Integer id){
        return repository.findById(id).orElseThrow();
    }

    public Category create(String name){
        return repository.save(new Category(name));
    }

    public Category update(Integer id, String name){
        Category category = findById(id);
        category.setName(name);
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
