package com.seed.util;

import com.seed.controllers.models.dao.UserDao;
import com.seed.models.Users;
import play.mvc.Controller;

/**
 * Created by prashant on 22/8/16.
 */
public class AuthenticatedUser extends Controller{

    protected final UserDao userDao;

    public AuthenticatedUser(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    public Users currentUser() {
        String email = session("email");
        if (email != null) {
            return userDao.findUserByEmail(email);
        }

        return null;
    }

}
