package com.revature.controllers;

import com.revature.models.User;
import com.revature.service.UserService;
import io.javalin.http.Context;

import java.util.List;

public class UserController {
    private UserService userService = new UserService();


    public void handleGetAllUsers(Context ctx){
        List<User> userList = userService.getAllUsers();
        if(userList.isEmpty()){
            ctx.status(403);
            ctx.result("There are no users");
        }
        ctx.json(userList);
    }



}
