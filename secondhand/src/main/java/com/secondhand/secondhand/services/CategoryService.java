package com.secondhand.secondhand.services;
import java.util.List;

import com.secondhand.secondhand.models.entities.Category;


public interface CategoryService{
    public Category addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategoryyById(Long id);
    Category getByIdCategory(Long id);
    List<Category> getAllCategory();
}
