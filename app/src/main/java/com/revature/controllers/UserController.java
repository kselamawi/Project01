package com.revature.controllers;

import com.revature.models.User;
import com.revature.service.UserService;
import io.javalin.http.Context;

import java.util.List;

import static java.lang.Integer.parseInt;

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


    public void handleGetUserById(Context ctx){
        User u = userService.getUserById(parseInt(ctx.pathParam("id")));
        System.out.println(u);
        if(u==null){
            ctx.result("Id entered does not exist in the database");
            ctx.status(400);
        }else{
           ctx.status(203);
           ctx.json(u);
        }
    }


    public void handleUpdateUserById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        User u = ctx.bodyAsClass(User.class);
        u.setId(id);

        if(u==null){
            ctx.status(400);
            ctx.result("Id doesn't exist in database");
        } else{
            ctx.status(200);
            ctx.result("You have updated user" + userService.updateUser(u));
        }
    }

}
