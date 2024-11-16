package com.tanvir.easyMart.controller;

import com.tanvir.easyMart.model.Category;
import com.tanvir.easyMart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
    public HttpEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public HttpEntity<String> CreateCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created",HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/admin/categories/{categoryId}")
    public HttpEntity<String> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
        try {
            String status = categoryService.updateCategory(categoryId,category);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

}