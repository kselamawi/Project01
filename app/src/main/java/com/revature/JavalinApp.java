package com.revature;

import com.revature.controllers.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;


public class JavalinApp {
    private UserController userController = new UserController();

    private Javalin app = Javalin.create().routes(()->{
        path("/users", () -> {
            get(userController::handleGetAllUsers);
        });

    });

    public void start(int port){
        app.start(port);
    }

}
