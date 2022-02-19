package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;


public class JavalinApp {
    private UserController userController = new UserController();
    private ReimbursementController reimbursementController = new ReimbursementController();
    private AuthController authController = new AuthController();

    private Javalin app = Javalin.create(config -> {config.enableCorsForAllOrigins();
        config.addStaticFiles("/static", Location.CLASSPATH);
    }).routes(() -> {
        path("/logout", () -> {
            get(authController::handleLogout);
        });
        path("/login", () -> {
           post(authController::handleLogin);
        });
        path("/register", () -> {
            post(userController::handleRegisterUser);
        });
        path("/users", () -> {
            get(userController::handleGetAllUsers);
            path("/{id}", () -> {
                get(userController::handleGetUserById);
                put(userController::handleUpdateUserById);
                path("/reimbursements", () -> {
                    get(reimbursementController::handleGetReimbursementsByAuthor);
                    path("/pending", () ->{
                        get(reimbursementController::handleGetAllPendingByUserID);
                    });
                    path("/resolved", () ->{
                        get(reimbursementController::handleGetResolvedByUserID );
                    });
                    path("/create", () ->{
                        post(reimbursementController::handleCreateReimbursement);
                    });
                });
            });
        });
        path("/reimbursements", () -> {
            get(reimbursementController::handleGetAllReimbursements);
            path("/pending", () -> {
                get(reimbursementController::handleGetAllReimbursementPending);
            });
            path("/resolved", () -> {
                get(reimbursementController::handleGetAllResolvedReimbursement);
            });
            path("/{id}", () -> {
                get(reimbursementController::handleGetReimbursementsById);
                path("/approve", () -> {
                    put(reimbursementController::handleApproveReimbursement);
                });
                path("/deny", () -> {
                    put(reimbursementController::handleDenyReimbursement);
                });
            });
        });

    });

    public void start(int port){
        app.start(port);
    }

}
