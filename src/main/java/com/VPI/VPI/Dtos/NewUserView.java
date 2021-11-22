package com.VPI.VPI.Dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NewUserView {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String rol;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
