package com.example.demo.controller.client;

import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.User;
import com.example.demo.domain.dto.EmailDto;
import com.example.demo.domain.dto.ResetPasswordDto;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;






@Controller
public class ForgotController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    public ForgotController(UserService userService,PasswordEncoder passwordEncoder,
    JavaMailSender javaMailSender){
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.javaMailSender=javaMailSender;
    }

    @GetMapping("/forgot-password")
    public String getForgotPasswordpage(Model model) {
        model.addAttribute("newEmail", new EmailDto());
        return "client/auth/forgot_password";
    }

    //Ham xu li phan gui email reset tai khoan
    private boolean sendEmail(String email, String resetLink) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("Click the link to reset your password: " + resetLink);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            return false;
        }
    }

    @PostMapping("/forgot-password")
    public String postConfirmEmail(Model model,
    @ModelAttribute("newEmail") @Valid EmailDto email,
    BindingResult bindingResult) {
        List<FieldError> errors=bindingResult.getFieldErrors();
        for(FieldError error:errors){
            System.out.println(">>>>>>"+error.getField()+"-"+error.getDefaultMessage());
        }


        if(bindingResult.hasErrors()){
            return "client/auth/forgot_password";
        }

        User user=this.userService.getUserByEmail1(email.getConfirmEmail());
        if (user == null) {
            model.addAttribute("error", "Email not found");
            return "client/auth/forgot_password";
        }
        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        this.userService.saveUser(user);

        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        if (sendEmail(email.getConfirmEmail(), resetLink)) {
            model.addAttribute("message", "A reset link has been sent to your email.");
        } else {
            model.addAttribute("error", "Failed to send email. Please check your email configuration and try again.");
        }

        return "client/auth/forgot_password";
    }
    
    
    @GetMapping("/reset-password")
    public String getResetPasswordPage(Model model,
    @RequestParam("token") String token) {
        model.addAttribute("newResetPassword", new ResetPasswordDto());
        model.addAttribute("token", token);
        return "client/auth/reset_password";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(@RequestParam("token") String token,
    @ModelAttribute("newResetPassword") @Valid ResetPasswordDto pass,
    BindingResult bindingResult,
    RedirectAttributes redirectAttributes) {
        User user=this.userService.getUserByPasswordToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid or expired token");
            return "redirect:/reset-password?token=" + token;
        }
        String hashPassword=this.passwordEncoder.encode(pass.getResetPassword());
        user.setPassword(hashPassword);
        user.setResetPasswordToken(null);
        //save user, luu password moi
        this.userService.saveUser(user);
        //model.addAttribute không giữ được giá trị khi redirect:, vì Spring MVC không lưu Model qua redirect. 
        redirectAttributes.addFlashAttribute("message", "Password has been reset successfully. Please login.");
        return "redirect:/login";
    }
    
    




}
