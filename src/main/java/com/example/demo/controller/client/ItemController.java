package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;




@Controller
public class ItemController {
    private final ProductService productService;

    public ItemController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/product/{id}")
    public String getMethodName(Model model, @PathVariable long id) { 
        Product item=this.productService.findProductById(id).get();
        model.addAttribute("item", item);
        model.addAttribute("id", id);
        return "client/product/detail";
    }
    
}
