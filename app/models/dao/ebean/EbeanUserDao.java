package models.dao.ebean;


import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;
import models.Users;
import models.dao.UserDao;

import java.util.List;

/**
 * Created by prashant on 25/7/16.
 */
public class EbeanUserDao extends AbstractEbeanDao<String, Users> implements UserDao {

    public EbeanUserDao() {
        super(new Finder<String,Users>(Users.class));
        }

    @Override
    public List<Users> findAllUsers(){
        return  finder.all();
    }

    @Override
    public Users findUserByEmail(String email){
        return finder.where().eq("email", email).findUnique();
    }
}
