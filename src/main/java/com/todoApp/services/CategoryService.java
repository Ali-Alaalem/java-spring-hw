package com.todoApp.services;

import com.todoApp.models.Category;
import com.todoApp.repositorys.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
 private CategoryRepository categoryRepository;

 @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category createCategory(Category objectCategory){
        return categoryRepository.save(objectCategory);
    }
    public Optional<Category> getCategoryById(Long categoryId){
     return categoryRepository.findById(categoryId);
    }

    public Category updateCategory(Long categoryId , Category objectCategory){
     Optional<Category> category = categoryRepository.findById(categoryId);
     category.get().setName(objectCategory.getName());
     category.get().setDescription(objectCategory.getDescription());

     return categoryRepository.save(category.get());

    }

    public void deleteCategory(Long categoryId){
     categoryRepository.deleteById(categoryId);
    }



}
