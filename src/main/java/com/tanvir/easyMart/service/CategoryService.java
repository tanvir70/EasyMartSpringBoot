package com.tanvir.easyMart.service;

import com.tanvir.easyMart.dto.CategoryDTO;
import com.tanvir.easyMart.dto.CategoryResponseDTO;
import com.tanvir.easyMart.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO  deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);

    CategoryDTO getCategory(Long id);
}
