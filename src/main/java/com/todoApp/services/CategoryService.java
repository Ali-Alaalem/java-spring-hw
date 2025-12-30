package com.todoApp.services;

import com.todoApp.exceptions.InformationNotFoundException;
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
        Category category = categoryRepository.findByName(objectCategory.getName());
        if(category!=null){
            throw new RuntimeException("category with name "+category.getName()+" already exist");
        }
        else {
            return categoryRepository.save(objectCategory);
        }
    }


    public Optional<Category> getCategoryById(Long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()){
            return categoryRepository.findById(categoryId);
        }else {
            throw new InformationNotFoundException("category with id "+categoryId +"Not found");
        }
    }

    public Category updateCategory(Long categoryId , Category objectCategory){
     Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            category.get().setName(objectCategory.getName());
            category.get().setDescription(objectCategory.getDescription());
        }else {
            throw new InformationNotFoundException("category with id "+categoryId +"Not found");
        }
     return categoryRepository.save(category.get());

    }

    public void deleteCategory(Long categoryId){
     categoryRepository.deleteById(categoryId);
    }



}
