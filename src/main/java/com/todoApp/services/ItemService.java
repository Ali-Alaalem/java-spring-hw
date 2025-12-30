package com.todoApp.services;

import com.todoApp.exceptions.InformationNotFoundException;
import com.todoApp.models.Category;
import com.todoApp.models.Item;
import com.todoApp.repositorys.CategoryRepository;
import com.todoApp.repositorys.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;



    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
@Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Item> getAllItems(Long categoryId){
        Optional<Category> category=categoryRepository.findById(categoryId);
        if (category.isPresent()){
            return category.get().getItems();
        }else {
            throw new InformationNotFoundException("No Items in Category with id "+categoryId);
        }
    }


    public Item createItem(Long categoryId,Item objectItem){
        Optional<Category> category=categoryRepository.findById(categoryId);

        if (category.isPresent()){
            objectItem.setCategory(category.get());
        }else {
            throw new InformationNotFoundException("category with id "+categoryId +"Not found");
        }

        return itemRepository.save(objectItem);
    }



    public Optional<Item> getItemById(Long itemId,Long categoryId){
       Optional<Item> item= itemRepository.findByIdAndCategoryId(itemId,categoryId);
       if(item.isPresent()){
           return item;
       }else{
           throw new InformationNotFoundException("Item with id "+itemId +"Not found");
       }
    }




    public Item updateItem(Long itemId , Long categoryId, Item objectItem){
        Optional<Item> item =
                itemRepository.findByIdAndCategoryId(itemId,categoryId);
        if(item.isPresent()){
            item.get().setName(objectItem.getName());
            item.get().setDueDate(objectItem.getDueDate());
            item.get().setDescription(objectItem.getDescription());
        }else{
            throw new InformationNotFoundException("Item with id "+itemId +"Not found");
        }
        return itemRepository.save(item.get());
    }


    @Transactional
    //All the remaining routes will use @Transactional automatically
    // because the Entity Manager mark the find operation with this annotation directly
    // but in the custom delete I did here there is no find first on it will be converted directly to sql query
    // and if no @Transactional Above it the Entity manager will never execute this sql command
    public void deleteCategory(Long itemId,Long categoryId){
        Optional<Item> item=itemRepository.findById(itemId);
        if(item.isPresent()){
            itemRepository.deleteByIdAndCategoryId(itemId,categoryId);
        }else{
            throw new InformationNotFoundException("Item with id "+itemId +"Not found");
        }

    }



}
