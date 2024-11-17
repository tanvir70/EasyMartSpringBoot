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

    //private List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = getAllCategories();
        Category deletedCategory = categories.stream()
                        .filter(c -> c.getCategoryId().equals(categoryId))
                        .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.delete(deletedCategory);
        return "Category with id " + categoryId + " deleted";
    }

    @Override
    public String updateCategory(Long categoryId, Category category) {
        List<Category> categories = getAllCategories();
        Category existingCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        existingCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existingCategory);
        return "Category with id " + categoryId + " updated";
    }
}
