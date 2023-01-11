package com.example.shoppinglist.web;

import com.example.shoppinglist.model.Product;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.session.LoggedUserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeContoller {

    private final LoggedUserSession loggedUserSession;
    private final ProductService productService;

    public HomeContoller(LoggedUserSession loggedUserSession, ProductService productService) {
        this.loggedUserSession = loggedUserSession;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUserSession.isLoggeinIn()) {
            return "redirect:/login";
        }

        List<Product> all = this.productService.findAll();
        List<Product> allFoods = this.productService.findAllFoods();
        List<Product> allDrinks = this.productService.findAllDrinks();
        List<Product> allHouseholds = this.productService.findAllHouseholds();
        List<Product> allOther = this.productService.findAllOther();

        long sum = all.stream().map(Product::getPrice).mapToLong(Long::longValue).sum();

        model.addAttribute("allFoods", allFoods);
        model.addAttribute("allDrinks", allDrinks);
        model.addAttribute("allHouseholds", allHouseholds);
        model.addAttribute("allOther", allOther);
        model.addAttribute("sum", sum);

        return "home";
    }


    @GetMapping("/buy-product/{id}")
    public String buyProduct(@PathVariable("id") long productId){
        if(!loggedUserSession.isLoggeinIn()){
            return "redirect:/login";
        }

        this.productService.deleteProductById(productId);
        return "redirect:/home";
    }

    @GetMapping("/buy-all")
    public String buyAll(){
        if(!loggedUserSession.isLoggeinIn()){
            return "redirect:/login";
        }

        this.productService.deleteAll();
        return "redirect:/home";
    }
}
