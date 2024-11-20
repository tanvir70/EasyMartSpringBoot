package com.tanvir.easyMart.service;

import com.tanvir.easyMart.dto.CategoryResponseDTO;
import com.tanvir.easyMart.exceptions.APIException;
import com.tanvir.easyMart.exceptions.ResourceNotFoundException;
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
    public CategoryResponseDTO getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("No Category Created yet !!!");
        }
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        Category categorySaved = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categorySaved != null) {
            throw new APIException("Category '" + category.getCategoryName() + "' already exists !!!!!!");
        }
        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category toBeDeletedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(toBeDeletedCategory);
        return "Category with ID " + categoryId + " deleted successfully.";
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        existingCategory.setCategoryName(category.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}

