package com.example.shoppinglist.init;

import com.example.shoppinglist.service.CategoryService;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    public DBInit(CategoryService categoryService, UserService userService, ProductService productService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.initCategoryDB();
        this.userService.initUsersDB();
        this.productService.initProductDB();
    }
}
