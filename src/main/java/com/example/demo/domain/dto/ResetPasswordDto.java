package com.example.demo.domain.dto;

import jakarta.validation.constraints.Size;

public class ResetPasswordDto {
    @Size(min=3, message="Password phai co toi thieu 3 ki tu")
    private String resetPassword;

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ResetPasswordDto{");
        sb.append("resetPassword=").append(resetPassword);
        sb.append('}');
        return sb.toString();
    }

}
