package models;

import play.data.validation.Constraints;

import static play.data.validation.Constraints.PatternValidator.message;

public class Login {

    @Constraints.Required(message = "Does'nt look like email to us")
    public String email;
    @Constraints.Required(message = "Enter the password")
    public String password;
}
