package com.tanvir.easyMart.service;

import com.tanvir.easyMart.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();
    void createCategory(Category category);

    String  deleteCategory(Long categoryId);

    String updateCategory(Long categoryId, Category category);
}
