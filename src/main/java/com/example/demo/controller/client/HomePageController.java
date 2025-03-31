package com.example.demo.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@Controller
public class HomePageController {
    private final ProductService productService;

    public HomePageController(ProductService productService){
        this.productService=productService;
    }


    @GetMapping("/homepage")
    public String getHomePage(Model model){
        List<Product>pros=this.productService.findAll();
        model.addAttribute("products", pros);
        return "client/homepage/show";
    }
}
