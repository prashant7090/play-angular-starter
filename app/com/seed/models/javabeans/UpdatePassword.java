package com.seed.models.javabeans;

/**
 * Created by prashant on 29/11/16.
 */
public class UpdatePassword {
    private String confirmPassword;
    private String password;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
