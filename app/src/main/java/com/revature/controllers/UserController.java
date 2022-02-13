package com.revature.controllers;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.service.UserService;
import io.javalin.http.Context;

import java.util.List;

import static java.lang.Integer.parseInt;

public class UserController {
    private UserDao userDao = new UserDaoImpl();
    private UserService userService = new UserService(userDao);

    public void handleGetAllUsers(Context ctx){
        List<User> userList = userService.getAllUsers();
        if(userList.isEmpty()){
            ctx.status(403);
            ctx.result("There are no users");
        }
        ctx.json(userList);
    }

    public void handleRegisterUser(Context ctx){
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");

        User user = new User(email, password, firstName, lastName);
        if(userService.createUser(user)){
            ctx.result("Your account has been created");
            ctx.status(200);
        } else {
            ctx.result("Your account could not be created");
            ctx.status(403);
        }
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
