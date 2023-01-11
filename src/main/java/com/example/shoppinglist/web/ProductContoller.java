package com.example.shoppinglist.web;

import com.example.shoppinglist.model.dto.ProductDTO;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.session.LoggedUserSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductContoller {

    private final LoggedUserSession loggedUserSession;
    private final ProductService productService;

    @Autowired
    public ProductContoller(LoggedUserSession loggedUserSession, ProductService productService) {
        this.loggedUserSession = loggedUserSession;
        this.productService = productService;
    }

    @ModelAttribute
    public ProductDTO initPostDTO() {
        return new ProductDTO();
    }

    @GetMapping("/add-product")
    public String addProduct(){
        if(!loggedUserSession.isLoggeinIn()){
            return "redirect:/login";
        }
        return "product-add";
    }

    @PostMapping("/add-product")
    public String addPost(@Valid ProductDTO productDTO, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("productDTO", productDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.productDTO", bindingResult);

            return "redirect:/add-product";
        }

        this.productService.addProduct(productDTO);

        return "redirect:/home";
    }

}
