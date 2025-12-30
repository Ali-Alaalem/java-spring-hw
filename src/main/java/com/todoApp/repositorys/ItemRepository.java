package com.todoApp.repositorys;

import com.todoApp.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {
   Optional<Item> findByIdAndCategoryId(Long itemId, Long categoryId);
   void deleteByIdAndCategoryId(Long itemId, Long categoryId);
}
