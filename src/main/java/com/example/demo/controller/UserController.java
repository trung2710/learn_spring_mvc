package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;






// @RestController
// public class UserController {

//     private UserService userService;
    
//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("")
//     public String handleHello() {
//         return this.userService.handleHello();
//     }
    
// }

@Controller
public class UserController{
    private final UserService userServise;

    public UserController(UserService userServise) {
        this.userServise = userServise;
    }

    @RequestMapping("/")
    public String getHomePage(Model model){ 
        String test=this.userServise.handleHello();
        List<User> arrUsers=this.userServise.getUserByEmail("TrungNVQ.B22KH126@stu.ptit.edu.vn");
        System.out.println(arrUsers);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getTableUsers(Model model) {
        List<User> arrUsers=this.userServise.getAllUsers();
        model.addAttribute("users1",arrUsers);
        return "admin/user/table-user";
    }

    @RequestMapping("admin/user/{id}")
    public String getDetailUser(Model model, @PathVariable long id) {
        User person=this.userServise.findUserById(id);
        System.out.println("id"+id);
        model.addAttribute("id",id);
        model.addAttribute("person",person);
        return "admin/user/show";
    }
    
    

    @RequestMapping("/admin/user/create")
    public String getAdminPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }    


    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model , @ModelAttribute("newUser") User trung){
        this.userServise.saveUser(trung);
        System.out.println("run here "+trung);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model,@PathVariable long id){
        User person=this.userServise.findUserById(id);
        model.addAttribute("newUser",person);
        return "admin/user/update";
    } 

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User person){
        User currentUser=this.userServise.findUserById(person.getId());
        if(currentUser!=null){
            currentUser.setAddress(person.getAddress());
            currentUser.setFullName(person.getFullName());
            currentUser.setPhone(person.getPhone());
        }
        this.userServise.saveUser(currentUser);
        return "redirect:/admin/user";
    } 


    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUser(Model model, @PathVariable long id) {
        model.addAttribute("id",id);
        User newUser=new User();
        newUser.setId(id);
        model.addAttribute("newUser", newUser);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User person) {
        this.userServise.deleteAUser(person.getId());
        return "redirect:/admin/user";
    }
    
    
       
}   
