package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.Category;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    List<Product> findAllByCategory(Category category);
}
