package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;

import java.util.List;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    //Create a User
    public boolean createUser(User user){
        return userDao.createUser(user);
    }
    //Delete a User
    public boolean deleteUser(int id){
        return userDao.deleteUser(id);
    }
    //Get all users
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
    //Get a user by ID.
    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    //Update user
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

}
