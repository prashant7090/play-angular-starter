package controllers;


import java.util.List;

import models.Login;
import models.Users;
import play.api.mvc.Security;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import play.mvc.Controller.*;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Model;

import static play.data.Form.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {	
        return ok(index.render("Your new application is ready."));
    }
    
    public Result login() {
        return ok(
            login.render()
        );
    }
    
    public Result authenticate(){
    	Form<Login> form = Form.form(Login.class).bindFromRequest();
    	Login login = form.get();
        String email = login.email;
        String password = login.password;

        if(email != null && password != null){
            Users find = new Users();
            Users user = new Model.Finder<>(String.class, Users.class).where().eq("email", email).findUnique();
            if(user != null && BCrypt.checkpw(password,user.password)){
                session().clear();
                session("email",user.email);
            }
            return ok();
        }
    	return badRequest();
    }
    
    public Result signUp(){
		Form<Users> form = Form.form(Users.class).bindFromRequest();
		Users user = form.get();
		user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
		user.save();
    	return redirect(routes.HomeController.index());
    }

    @play.mvc.Security.Authenticated(Secured.class)
    public Result getUsers(){
    	List<Users> users = new Model.Finder<>(String.class, Users.class).all();
    	
    	return ok(Json.toJson(users));
    }
    

}
