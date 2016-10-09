package play.util;

import com.google.inject.Inject;
import models.Users;
import models.dao.DaoProvider;
import models.dao.UserDao;
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
