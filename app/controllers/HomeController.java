package controllers;


import java.util.List;

import com.google.inject.Inject;
import models.Login;
import models.Users;
import models.dao.DaoProvider;
import models.dao.UserDao;
import play.auth.AdminAuthenticator;
import play.auth.Secured;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.util.AuthenticatedUser;
import views.html.*;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Model;

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
    DaoProvider provider;
    @Inject
    FormFactory formFactory;

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

            if(email != null && password != null){
                UserDao userDao = provider.userDao();
                Users user = userDao.findUserByEmail(email);
                if(user != null && BCrypt.checkpw(password,user.password)){
                    session().clear();
                    session("email",user.email);
                    if(user.role!= null && user.role.equals("admin")){
                        session("role","admin");
                    }
                    return redirect(routes.HomeController.index());
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
        UserDao userDao = provider.userDao();
        List<Users> users = userDao.findAllUsers();
    	return ok(Json.toJson(users));
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.HomeController.login()
        );
    }

}
