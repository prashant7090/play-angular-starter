package models.dao;

import models.Users;

import java.util.List;

/**
 * Created by prashant on 25/7/16.
 */
public interface UserDao {
    List<Users> findAllUsers();

}
