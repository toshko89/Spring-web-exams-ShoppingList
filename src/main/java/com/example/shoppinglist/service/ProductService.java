package com.example.shoppinglist.service;

import com.example.shoppinglist.model.Category;
import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.model.enums.CategoryEnum;
import com.example.shoppinglist.repository.ProductRepo;
import com.example.shoppinglist.session.LoggedUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryService categoryService;
    private final LoggedUserSession loggedUserSession;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryService categoryService, LoggedUserSession loggedUserSession) {
        this.productRepo = productRepo;
        this.categoryService = categoryService;
        this.loggedUserSession = loggedUserSession;
    }


    public void initProductDB() {
        if (productRepo.count() == 0) {

            Product product = new Product()
                    .setCategory(this.categoryService.getCategory(CategoryEnum.DRINK))
                    .setDescription("Ne znam kakvo da napisha")
                    .setName("Vodka")
                    .setNeededBefore(LocalDateTime.now())
                    .setPrice(10L);
            this.productRepo.save(product);

            Product product1 = new Product()
                    .setCategory(this.categoryService.getCategory(CategoryEnum.FOOD))
                    .setDescription("Ne znam kakvo da napisha")
                    .setName("Burger")
                    .setNeededBefore(LocalDateTime.now())
                    .setPrice(10L);
            this.productRepo.save(product1);

            Product product2 = new Product()
                    .setCategory(this.categoryService.getCategory(CategoryEnum.OTHER))
                    .setDescription("Ne znam kakvo da napisha")
                    .setName("Kotka")
                    .setNeededBefore(LocalDateTime.now())
                    .setPrice(55L);
            this.productRepo.save(product2);

            Product product3 = new Product()
                    .setCategory(this.categoryService.getCategory(CategoryEnum.HOUSEHOLD))
                    .setDescription("Ne znam kakvo da napisha")
                    .setName("Shkaf")
                    .setNeededBefore(LocalDateTime.now())
                    .setPrice(100L);
            this.productRepo.save(product3);
        }
    }

    public void addProduct(ProductDTO productDTO) {
        long userId = this.loggedUserSession.getId();
        Category category = this.categoryService.getCategory(CategoryEnum.valueOf(productDTO.getCategory()));

        Product product = new Product()
                .setPrice(productDTO.getPrice())
                .setName(productDTO.getName())
                .setDescription(productDTO.getDescription())
                .setCategory(category)
                .setNeededBefore(productDTO.getNeededBefore());

        this.productRepo.save(product);

    }

    public List<Product> findAll() {
        return this.productRepo.findAll();
    }

    public List<Product> findAllFoods(){
        return this.productRepo.findAllByCategory(this.categoryService.getCategory(CategoryEnum.FOOD));
    }

    public List<Product> findAllDrinks() {
        return this.productRepo.findAllByCategory(this.categoryService.getCategory(CategoryEnum.DRINK));
    }

    public List<Product> findAllHouseholds() {
        return this.productRepo.findAllByCategory(this.categoryService.getCategory(CategoryEnum.HOUSEHOLD));
    }

    public List<Product> findAllOther() {
        return this.productRepo.findAllByCategory(this.categoryService.getCategory(CategoryEnum.OTHER));
    }

    public void deleteProductById(long productId) {
        this.productRepo.deleteById(productId);
    }

    public void deleteAll() {
        this.productRepo.deleteAll();
    }
}
