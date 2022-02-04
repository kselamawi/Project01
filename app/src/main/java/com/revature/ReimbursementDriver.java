package com.revature;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.User;
import com.revature.models.UserRole;
import io.javalin.Javalin;

import java.sql.Connection;
import java.util.List;

public class ReimbursementDriver {


    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);


        ReimbursementDao reimbursementDao = new ReimbursementDaoImpl();
        UserDao userDao = new UserDaoImpl();

        //Testing creating a user.
 //       User testUser = new User("Jeremiah", "Grimes", UserRole.MANAGER, "jeremiah.grimes@email.com", "password");
   //     userDao.createUser(testUser);

        //Testing deleting a user.
//        userDao.deleteUser(2);

        //Testing Updating user.
        List<User> userList = userDao.getAllUsers();
        System.out.println(userList);
        User testUser = new User("Selamawi", "Kahsai", UserRole.MANAGER, "selamawi.kahsai@email.com", "password");
        testUser.setId(3);
        userDao.updateUser(testUser);

    }

}
