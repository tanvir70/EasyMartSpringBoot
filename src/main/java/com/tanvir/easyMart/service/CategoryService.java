package com.tanvir.easyMart.service;

import com.tanvir.easyMart.dto.CategoryDTO;
import com.tanvir.easyMart.dto.CategoryResponseDTO;
import com.tanvir.easyMart.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO getAllCategories();

    Category createCategory(Category category);

    String  deleteCategory(Long categoryId);

    Category updateCategory(Long categoryId, Category category);
}
