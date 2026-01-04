package com.todoApp.services;

import com.todoApp.exceptions.InformationNotFoundException;
import com.todoApp.models.Category;
import com.todoApp.models.User;
import com.todoApp.repositorys.CategoryRepository;
import com.todoApp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public static User getCurrentLoggedInUser(){
        MyUserDetails userDetails=(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    public List<Category> getAllCategories(){
        List<Category> category = categoryRepository.findByUserId(CategoryService.getCurrentLoggedInUser().getId());
        return category;
    }

    public Category createCategory(Category categoryObject){
        Category category = categoryRepository.findByUserIdAndName(
                CategoryService.getCurrentLoggedInUser().getId()
                ,categoryObject.getName());
        if(category!=null){
            throw new RuntimeException("category with name "+category.getName()+" already exist");
        }
        else {
            categoryObject.setUser(getCurrentLoggedInUser());
            return categoryRepository.save(categoryObject);
        }

    }


    public Optional<Category> getCategoryById(Long categoryId){

        Optional<Category> category = Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId()));
        if (category.isPresent()){
            return category;
        }else {
            throw new InformationNotFoundException("category with id "+categoryId +"Not found");
        }
    }

    public Category updateCategory(Long categoryId , Category objectCategory){
     Optional<Category> category =  Optional.ofNullable(categoryRepository.findByIdAndUserId(categoryId, CategoryService.getCurrentLoggedInUser().getId()));

        if (category.isPresent()) {
            category.get().setName(objectCategory.getName());
            category.get().setDescription(objectCategory.getDescription());
        }else {
            throw new InformationNotFoundException("category with id "+categoryId +"Not found");
        }
     return categoryRepository.save(category.get());

    }

    public void deleteCategory(Long categoryId){
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.get().getUser().getId().equals(CategoryService.getCurrentLoggedInUser().getId())) {
            categoryRepository.deleteById(categoryId);
        }else{
            throw new InformationNotFoundException("You dont own this category");
        }
    }



}
