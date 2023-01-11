package com.example.shoppinglist.model.dto;

import com.example.shoppinglist.model.enums.CategoryEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class ProductDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String description;

    @NotNull
    @Positive
    private Long price;

    @NotNull
    @PastOrPresent
    private LocalDateTime neededBefore;

    @NotNull
    private String category;

    public ProductDTO() {
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public ProductDTO setPrice(Long price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public ProductDTO setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", neededBefore=" + neededBefore +
                ", category=" + category +
                '}';
    }
}
