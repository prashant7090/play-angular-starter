package controllers;


import java.util.List;

import models.Users;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;
import com.avaje.ebean.Model;

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
    
    public Result signUp(){
		Form<Users> form = Form.form(Users.class).bindFromRequest();
		Users user = form.get();
		user.password = BCrypt.hashpw(user.password, BCrypt.gensalt());
		user.save();
    	return redirect(routes.HomeController.index());
    }
    
    public Result getUsers(){
    	List<Users> users = new Model.Finder<>(String.class, Users.class).all();
    	
    	return ok(Json.toJson(users));
    }
    

}
