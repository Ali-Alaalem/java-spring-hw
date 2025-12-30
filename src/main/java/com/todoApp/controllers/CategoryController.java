package com.todoApp.controllers;

import com.todoApp.models.Category;
import com.todoApp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

   @GetMapping("categories/")
    public List<Category> getAllCategories(){
return categoryService.getAllCategories();
   }

    @PostMapping("categories/")
    public Category createCategory(@RequestBody Category categoryObject){
        return categoryService.createCategory(categoryObject);
    }

    @GetMapping("categories/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable(value="categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("categories/{categoryId}")
    public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId , @RequestBody Category objectCategory){
        return categoryService.updateCategory(categoryId,objectCategory);
    }

    @DeleteMapping("categories/{categoryId}")
    public void deleteCategory(@PathVariable(value = "categoryId") Long categoryId ){
        categoryService.deleteCategory(categoryId);
    }


}
