package com.secondhand.secondhand.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondhand.secondhand.models.entities.Category;
import com.secondhand.secondhand.models.repos.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService  {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getByIdCategory(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryRepository.findById(category.getCategoryId()).get();
        this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryyById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
