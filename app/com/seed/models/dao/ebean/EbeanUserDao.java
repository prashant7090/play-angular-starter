package com.seed.models.dao.ebean;


import com.seed.controllers.models.dao.UserDao;
import com.avaje.ebean.Model.Finder;
import com.seed.models.Users;

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

    @Override
    public Users findUserByToken(String token) {
        return finder.where().eq("token",token).findUnique();
    }
}
