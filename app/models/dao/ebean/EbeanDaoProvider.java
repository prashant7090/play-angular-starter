package models.dao.ebean;

import models.dao.DaoProvider;
import models.dao.UserDao;

/**
 * Created by prashant on 28/7/16.
 * Class to hold the reference of all the provider
 */
public class EbeanDaoProvider  implements DaoProvider{

    private final UserDao userDao = new EbeanUserDao();

    @Override
    public UserDao userDao() {
        return userDao;
    }
}
