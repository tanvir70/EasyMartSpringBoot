package com.tanvir.easyMart.repositories;

import com.tanvir.easyMart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);
}
