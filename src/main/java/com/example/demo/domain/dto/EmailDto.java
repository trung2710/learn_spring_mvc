package com.example.demo.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmailDto {
    
    @NotNull
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String confirmEmail;

    public String getConfirmEmail() {
        return confirmEmail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmailDto{");
        sb.append("confirmEmail=").append(confirmEmail);
        sb.append('}');
        return sb.toString();
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

}
