package com.seed.controllers;


import java.util.List;

import com.google.inject.Inject;
import com.seed.models.javabeans.ForGotPassword;
import com.seed.models.javabeans.Login;
import com.seed.models.javabeans.UpdatePassword;
import com.seed.models.Users;
import com.seed.controllers.models.dao.DaoProvider;
import com.seed.controllers.models.dao.UserDao;
import com.seed.auth.Secured;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Result;
import com.seed.util.AuthenticatedUser;
import views.html.*;

import org.mindrot.jbcrypt.BCrypt;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends AuthenticatedUser {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */


    @Inject
    private FormFactory formFactory;

    private final UserDao userDao;

    @Inject
    HomeController(DaoProvider provider){
        super(provider.userDao());
        this.userDao = provider.userDao();
    }

    @play.mvc.Security.Authenticated(Secured.class)
    public Result index() {	
        return ok(index.render());
    }
    
    public Result login() {
        return ok(
            login.render(null,"")
        );
    }


    public Result signUp(){
        return ok(
                signup.render(null,"")
        );
    }
    
    public Result authenticate(){
    	Form<Login> form = formFactory.form(Login.class).bindFromRequest();

        if (form.hasErrors()) {
            return badRequest(login.render(form,""));
        }else{
            Login login = form.get();
            String email = login.email.toLowerCase();
            String password = login.password;

            if(password != null){
                Users user = userDao.findUserByEmail(email);
                if(user != null && BCrypt.checkpw(password,user.password)){
                    session().clear();
                    session("email",user.email);
                    if(user.role!= null && user.role.equals("admin")){
                        session("role","admin");
                    }
                    return redirect(com.seed.controllers.routes.HomeController.index());
                }

            }
            return badRequest(views.html.login.render(form,"error"));
        }

    }
    
    public Result register(){
		Form<Users> form = formFactory.form(Users.class).bindFromRequest();
        if (form.hasErrors()) {
           return badRequest(signup.render(form,""));
        }
		Users user = form.get();
        if(!(user.password.equals(user.confirmpassword))){
            return badRequest(signup.render(form,"error"));
        }
        user.email = form.get().email.toLowerCase();
		user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
		user.save();
    	return redirect(routes.HomeController.login());
    }

    @play.mvc.Security.Authenticated(Secured.class)
    public Result getUsers(){
        List<Users> users = userDao.findAllUsers();
    	return ok(Json.toJson(users));
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                com.seed.controllers.routes.HomeController.login()
        );
    }

    public Result forgotPassword(){
        Form<ForGotPassword> forgotPasswordForm = formFactory.form(ForGotPassword.class).bindFromRequest();
        if (forgotPasswordForm.hasErrors())
            return badRequest("Enter your email id ");
        ForGotPassword forGotPasswordForm = forgotPasswordForm.get();
        Users user = userDao.findUserByEmail(forGotPasswordForm.getEmail());
        if(user != null ){
            Long randomNumber = getRandomNumber();
            user.setToken(randomNumber);
            user.update();
            return ok(Json.toJson(generateForgotPasswordLink(randomNumber.toString())));
        }
        return badRequest("No User Found with this email id");
    }

    private Long getRandomNumber(){
        return ThreadLocalRandom.current().nextLong(111111111, 999999999 + 1);
    }

    public String generateForgotPasswordLink(String token){
        return request()._underlyingRequest().host() + "/forgot-password/" + token;
    }

    public Result updatePassword(Long token){
        Form<UpdatePassword> updatePassword = formFactory.form(UpdatePassword.class).bindFromRequest();
        UpdatePassword updatePasswordForm = updatePassword.get();
        String password = updatePasswordForm.getPassword();
        String confirmPassword = updatePasswordForm.getConfirmPassword();
        if(!(password.equals(confirmPassword))){
            return badRequest("Password mismatch");
        }
        Users user = userDao.findUserByToken(token.toString());
        if (user != null){
            user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
            user.update();
            return ok(Json.toJson("your password is updated successfully"));
        }
        return badRequest("No User Found!");
    }

}
