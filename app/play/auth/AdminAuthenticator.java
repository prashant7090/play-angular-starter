package play.auth;


import controllers.routes;
import play.common.Roles;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by prashant on 17/8/16.
 */
public class AdminAuthenticator extends play.mvc.Security.Authenticator {
    private final String role;

    public AdminAuthenticator(){
        this.role = Roles.ADMIN;
    }

    @Override
    public String getUsername(Http.Context ctx){
        String userEmail = ctx.session().get("email");
        String userRole =ctx.session().get("role");
        if(userEmail != null && role.equals(userRole)){
            return "Jiklayis";
        }else{
            return null;
        }
    }


    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.HomeController.login());
    }



}
