
package com.seed.auth;

/**
 * Created by prashant on 18/7/16.
 */

import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(com.seed.controllers.routes.HomeController.login());
    }
}