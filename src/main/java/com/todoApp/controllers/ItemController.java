package com.todoApp.controllers;


import com.todoApp.models.Category;
import com.todoApp.models.Item;
import com.todoApp.services.CategoryService;
import com.todoApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("categories/{categoryId}/items")
    public List<Item> getAllItemsForOneCategory(@PathVariable(value="categoryId") Long categoryId){
        return itemService.getAllItems(categoryId);
    }
    @PostMapping("categories/{categoryId}/items")
    public Item createItem(@PathVariable(value="categoryId") Long categoryId,@RequestBody Item itemObject){
        return itemService.createItem(categoryId,itemObject);
    }
    @GetMapping("categories/{categoryId}/items/{itemId}")
    public Optional<Item> getItemById(@PathVariable(value="categoryId") Long categoryId,@PathVariable(value="itemId") Long itemId){
        return itemService.getItemById(itemId,categoryId);
    }
    @PutMapping("categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable(value = "categoryId") Long categoryId,@PathVariable(value="itemId") Long itemId , @RequestBody Item objectCItem){
        return itemService.updateItem(itemId,categoryId,objectCItem);
    }
    @DeleteMapping("categories/{categoryId}/items/{itemId}")
    public void deleteItem(@PathVariable(value = "categoryId") Long categoryId,@PathVariable(value="itemId") Long itemId){
        itemService.deleteCategory(itemId,categoryId);
    }



}