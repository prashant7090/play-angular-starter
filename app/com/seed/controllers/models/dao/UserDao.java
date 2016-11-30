package com.seed.controllers.models.dao;

import com.seed.models.Users;

import java.util.List;

/**
 * Created by prashant on 25/7/16.
 */
public interface UserDao extends  Dao<String, Users>{
    List<Users> findAllUsers();
    Users findUserByEmail(String email);
    Users findUserByToken(String token);
}
