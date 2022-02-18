package com.revature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.LoggingUtil;
import io.javalin.core.util.Header;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {
    private UserDao userDao = new UserDaoImpl();
    private UserService userService = new UserService(userDao);
    private ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
    private ReimbursementService reimbursementService = new ReimbursementService(reimbursementDao);
    private ObjectMapper mapper = new ObjectMapper();


    public void handleLogin(Context ctx) throws JsonProcessingException {
        LoginObject lo = mapper.readValue(ctx.body(), LoginObject.class);

        LoggingUtil.logger.info("Attempting to login");

        User user = userService.getUserByEmailAndPassword(lo.email, lo.password);
        if(user==null){
            throw new UnauthorizedResponse("Incorrect username and password");
        }
        LoggingUtil.logger.info("User " + user.getEmail() + " has logged in");
        ctx.result("Logged in");
        String authKey = String.valueOf(user.getUserRole());
        String userToken = String.valueOf(user.getId());

        //Lets set the session to know that the person is logged in
        ctx.req.getSession().setAttribute("id", userToken);
        ctx.req.getSession().setAttribute("authorization", authKey);

        ctx.header("Authorization", authKey);
        ctx.header("Id", userToken);
    }

    public void authorizeEmployeeToken(Context ctx){
        LoggingUtil.logger.info("Verifying employee is logged in");
        ctx.header("Access-Control-Expose-Headers", "*");

        if(ctx.header("Authorization")!= null){
            if(ctx.header("Authorization").equals("EMPLOYEE") || ctx.header("Authorization").equals("MANAGER")) {
                LoggingUtil.logger.info("User has Employee authorization");
                ctx.status(200);
            } else {
                throw new UnauthorizedResponse("You need to be an employee to use this feature");
            }
        } else {
            throw new UnauthorizedResponse("You need to login to use this feature");
        }

    }

    public void authorizeManagerToken(Context ctx){
        LoggingUtil.logger.info("Verifying manager is logged in");
        ctx.header("Access-Control-Expose-Headers", "*");

        if(ctx.header("Authorization")!= null){
            if(ctx.header("Authorization").equals("MANAGER")) {
                LoggingUtil.logger.info("User has Manager authorization");
            } else {
                throw new UnauthorizedResponse("You need to be a manager to use this feature");
            }
        } else {
            throw new UnauthorizedResponse("You need to login to use this feature");
        }

    }

}
class LoginObject{
    public String email;
    public String password;
}
