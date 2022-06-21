package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.models.repos.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category newCategory) {
        return this.categoryRepository.save(newCategory);
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryRepository.findById(category.getCategoryId()).get();
        this.categoryRepository.save(category);
        
    }

    @Override
    public void deleteCategoryById(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public Category getById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

}
