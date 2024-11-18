package com.tanvir.easyMart.service;

import com.tanvir.easyMart.model.Category;
import com.tanvir.easyMart.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category toBeDeletedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.delete(toBeDeletedCategory);
        return "Category with ID " + categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}

