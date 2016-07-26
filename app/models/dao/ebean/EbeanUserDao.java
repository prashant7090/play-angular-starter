package models.dao.ebean;

import com.avaje.ebean.Model;
import models.Users;
import models.dao.UserDao;

import java.util.List;

/**
 * Created by prashant on 25/7/16.
 */
public class EbeanUserDao implements UserDao {

    @Override
    public List<Users> findAllUsers(){
        return new Model.Finder<>(String.class, Users.class).all();
    }
}
