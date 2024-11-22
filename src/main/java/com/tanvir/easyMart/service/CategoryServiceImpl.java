package com.tanvir.easyMart.service;

import com.tanvir.easyMart.dto.CategoryDTO;
import com.tanvir.easyMart.dto.CategoryResponseDTO;
import com.tanvir.easyMart.exceptions.APIException;
import com.tanvir.easyMart.exceptions.ResourceNotFoundException;
import com.tanvir.easyMart.model.Category;
import com.tanvir.easyMart.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDTO getAllCategories(Integer pageNumber, Integer pageSize) {
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
        List<Category> categories = categoryPage.getContent();
        if (categories.isEmpty()) {
            throw new APIException("Resource Not found on this page !!!");
        }
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategories(categoryDTOS);
        categoryResponseDTO.setPageNumber(categoryPage.getNumber());
        categoryResponseDTO.setPageSize(categoryPage.getSize());
        categoryResponseDTO.setTotalElements(categoryPage.getTotalElements());
        categoryResponseDTO.setTotalPages(categoryPage.getTotalPages());
        categoryResponseDTO.setLastPage(categoryPage.isLast());
        return categoryResponseDTO;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categorySaved = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categorySaved != null) {
            throw new APIException("Category '" + category.getCategoryName() + "' already exists !!!!!!");
        }
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO categoryDTOSaved = modelMapper.map(savedCategory, CategoryDTO.class);
        return categoryDTOSaved;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category toBeDeletedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(toBeDeletedCategory);
        CategoryDTO deletedCategoryDTO = modelMapper.map(toBeDeletedCategory, CategoryDTO.class);
        return deletedCategoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Category category = modelMapper.map(categoryDTO, Category.class);
        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory = categoryRepository.save(existingCategory);
        CategoryDTO categoryDTOSaved = modelMapper.map(existingCategory, CategoryDTO.class);
        return categoryDTOSaved;
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
        CategoryDTO categoryDTOSaved = modelMapper.map(existingCategory, CategoryDTO.class);
        return categoryDTOSaved;
    }
}

