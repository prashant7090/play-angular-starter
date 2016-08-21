package models.dao;

import models.Users;
import org.h2.engine.User;

import java.util.List;

/**
 * Created by prashant on 25/7/16.
 */
public interface UserDao extends  Dao<String, Users>{
    List<Users> findAllUsers();
    Users findUserByEmail(String email);
}
