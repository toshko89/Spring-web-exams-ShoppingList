package com.example.shoppinglist.service;

import com.example.shoppinglist.model.Category;
import com.example.shoppinglist.model.enums.CategoryEnum;
import com.example.shoppinglist.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category getCategory(CategoryEnum categoryEnum) {
        return this.categoryRepo.findByCategoryName(categoryEnum);
    }

    public void initCategoryDB() {
        if (categoryRepo.count() == 0) {
            List<Category> categoryList = Arrays.stream(CategoryEnum.values())
                    .map(categoryEnum -> new Category().setCategoryName(categoryEnum))
                    .toList();

            categoryRepo.saveAll(categoryList);
        }
    }
}
