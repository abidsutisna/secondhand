package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.Category;
import java.util.List;

public interface CategoryService {
    public Category addCategory(Category newCategory);
    void updateCategory(Category category);
    void deleteCategoryById(Long id);
    Category getById(Long categoryId);
    List<Category> getAllCategories();
    
}
