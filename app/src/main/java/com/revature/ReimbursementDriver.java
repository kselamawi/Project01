package com.revature;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import io.javalin.Javalin;

public class ReimbursementDriver {


    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);


        ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
        UserDao userDao = new UserDaoImpl();

        


    }

}
