package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.models.User;

import java.util.List;

public class UserService {
    private UserDao userDao;
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }


    // ---verifying login
    public User getUserByEmailAndPassword(String email, String password){
        User user = userDao.getUserByEmailAndPassword(email, password);
        if(user != null){
            return user;
        }
        return null;
    }

    


    //----Create a User
    public boolean createUser(User user){
        return userDao.createUser(user);
    }
    //----Delete a User
    public boolean deleteUser(int id){

        return userDao.deleteUser(id);
    }
    //----Get all users
    public List<User> getAllUsers(){

        return userDao.getAllUsers();
    }
    //---Get a user by ID.
    public User getUserById(int id){

        return userDao.getUserById(id);
    }

    //check if user exists
    //public boolean doesUserExist(User u){

    //}

    //----Update user
    public boolean updateUser(User user) {

        return userDao.updateUser(user);
    }

}
