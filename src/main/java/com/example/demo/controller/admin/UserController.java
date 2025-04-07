package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.User;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;




@Controller
public class UserController{
    private final UserService userServise;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userServise,UploadService uploadService,PasswordEncoder passwordEncoder) {
        this.userServise = userServise;
        this.uploadService=uploadService;
        this.passwordEncoder=passwordEncoder;
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
        return "admin/user/show";
    }

    @RequestMapping("admin/user/{id}")
    public String getDetailUser(Model model, @PathVariable long id) {
        User person=this.userServise.findUserById(id);
        System.out.println("id"+id);
        model.addAttribute("id",id);
        model.addAttribute("person",person);
        return "admin/user/detail";
    }
    
    

    @RequestMapping("/admin/user/create")
    public String getAdminPage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }    


    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model ,
     @ModelAttribute("newUser") @Valid User trung,
     BindingResult newUserBindingResult,
     @RequestParam("avatarfile") MultipartFile file
     ){
        //ket qua cua qua trinh valid, duoc  lay thong qua blindingresult
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>"+error.getField() + " - " + error.getDefaultMessage());
        } 

        if(newUserBindingResult.hasErrors()){
            return "admin/user/create";
        }
        //validate
        String avatarName=this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword=this.passwordEncoder.encode(trung.getPassword());
        trung.setAvatar(avatarName);
        trung.setPassword(hashPassword);
        trung.setRole(this.userServise.getRoleByName(trung.getRole().getName()));
        this.userServise.saveUser(trung);
        System.out.println("run here "+trung);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model,@PathVariable long id){
        User person=this.userServise.findUserById(id);
        //model giúp truyền dữ liệu từ Controller sang view đê thực hiện các theo tác ở the view.
        model.addAttribute("newUser",person);
        return "admin/user/update";
    } 

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model,
    @ModelAttribute("newUser") @Valid User person,
    BindingResult newUserBindingResult,
    @RequestParam("avatarfile") MultipartFile file) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors ) {
            System.out.println (">>>"+error.getField() + " - " + error.getDefaultMessage());
        } 

        //validate
        if(newUserBindingResult.hasErrors()){
            return "admin/user/update";
        }

        User currentUser=this.userServise.findUserById(person.getId());
        if(currentUser!=null){
            //update new avatar
            if(!file.isEmpty()){
                String avatarName=this.uploadService.handleSaveUploadFile(file, "avatar");
                currentUser.setAvatar(avatarName);
            }

            currentUser.setAddress(person.getAddress());
            currentUser.setFullName(person.getFullName());
            currentUser.setPhone(person.getPhone());
            currentUser.setRole(this.userServise.getRoleByName(person.getRole().getName()));
            this.userServise.saveUser(currentUser);
        }
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
