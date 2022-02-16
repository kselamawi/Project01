package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;


public class JavalinApp {
    private UserController userController = new UserController();
    private ReimbursementController reimbursementController = new ReimbursementController();
    private AuthController authController = new AuthController();

    private Javalin app = Javalin.create(config -> {config.enableCorsForAllOrigins();
    }).routes(() -> {
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
                        get(reimbursementController::handleCreateReimbursement);
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
        //Reimbursements authorization for Managers.
        before("/reimbursements", authController::authorizeManagerToken);
        before("/reimbursements/{id}", authController::authorizeManagerToken);
        before("/reimbursements/pending", authController::authorizeManagerToken);
        before("/reimbursements/resolved", authController::authorizeManagerToken);
        before("/reimbursements/{id}/approve", authController::authorizeManagerToken);
        before("/reimbursements/{id}/deny", authController::authorizeManagerToken);
        //Users authorization for Users and Managers
        before("/users", authController::authorizeManagerToken);
        before("/users/{id}", authController::authorizeEmployeeToken);
        before("/users/{id}/reimbursements", authController::authorizeEmployeeToken);
        before("/users/{id}/reimbursements/pending", authController::authorizeEmployeeToken);
        before("/users/{id}/reimbursements/resolved", authController::authorizeEmployeeToken);
        before("/users/{id}/reimbursements/create", authController::authorizeEmployeeToken);

    });

    public void start(int port){
        app.start(port);
    }

}
