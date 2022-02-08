package com.revature.dao;

import com.revature.models.User;

import java.util.List;

public interface UserDao {

    //Create a Reimbursement
    public boolean createUser(User user);
    //Delete a Reimbursement
    public boolean deleteUser(int id);
    //Update a Reimbursement
    public boolean updateUser(User user);
    //Get all reimbursements
    public List<User> getAllUsers();
    //Get a reimbursement by ID.
    public User getUserById(int id);
    public User getUserByEmailAndPassword( String email, String password);

}
