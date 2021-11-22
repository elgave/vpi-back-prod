package com.VPI.VPI.Dtos;

import javax.validation.constraints.NotBlank;

public class ChangePassView {

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPass;
    @NotBlank
    private String passwordTemporal;

    public ChangePassView() {
    }

    public ChangePassView(String password, String confirmPass, String passwordTemporal) {
        this.password = password;
        this.confirmPass = confirmPass;
        this.passwordTemporal = passwordTemporal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getPasswordTemporal() {
        return passwordTemporal;
    }

    public void setPasswordTemporal(String passwordTemporal) {
        this.passwordTemporal = passwordTemporal;
    }
}
