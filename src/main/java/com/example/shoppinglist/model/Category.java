package com.example.shoppinglist.model;

import com.example.shoppinglist.model.enums.CategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_name",unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryName;

    private String description;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryEnum getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(CategoryEnum categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
