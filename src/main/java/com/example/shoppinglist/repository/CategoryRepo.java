package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.Category;
import com.example.shoppinglist.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByCategoryName(CategoryEnum categoryEnum);
}
