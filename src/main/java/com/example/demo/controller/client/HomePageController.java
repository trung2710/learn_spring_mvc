package com.example.demo.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.RegisterDto;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;





@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService,UserService userService,
    PasswordEncoder passwordEncoder){
        this.productService=productService;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }


    @GetMapping("/homepage")
    public String getHomePage(Model model){
        List<Product>pros=this.productService.findAll();
        model.addAttribute("products", pros);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDto());
        return "client/auth/register";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @PostMapping("/register")
    public String postRegisterUser(@ModelAttribute("registerUser") @Valid RegisterDto res,
    BindingResult bindingResult) {

        List<FieldError> errors=bindingResult.getFieldErrors();
        for(FieldError error:errors){
            System.out.println(">>>>>>"+error.getField()+"-"+error.getDefaultMessage());
        }

        if(bindingResult.hasErrors()){
            return "client/auth/register";
        }

        User user=this.userService.changeRegisterDtoToUser(res);
        String hashPassword=this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));

        //save user
        this.userService.saveUser(user);
        return "redirect:/login";
    }   
    
    @GetMapping("/access-deny")
    public String getAccessDenyPage() {
        return "client/auth/access_deny";
    }
    
    
}
