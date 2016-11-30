package com.seed.models;

import play.data.validation.Constraints;

/**
 * Created by prashant on 29/11/16.
 */
public class ForGotPassword {

    @Constraints.Required
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
