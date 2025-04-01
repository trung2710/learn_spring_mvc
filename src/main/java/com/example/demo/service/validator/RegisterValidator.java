package com.example.demo.service.validator;


import com.example.demo.domain.dto.RegisterDto;
import com.example.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// @Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDto>{

    private final UserService userService;
    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(RegisterDto user, ConstraintValidatorContext context){
        boolean valid=true;

        //check if password fields match
        if(!user.getPassword().equals(user.getConfirmPassword())){
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid=false;
        }
        //check email
        if(this.userService.checkEmailExist(user.getEmail())){
            context.buildConstraintViolationWithTemplate("Email already exists")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid=false;
        }

        return valid;
    }
}
