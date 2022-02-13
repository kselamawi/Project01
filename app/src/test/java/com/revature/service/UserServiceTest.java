package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    UserDao userDao;
    ReimbursementDao reimbursementDao;

    @InjectMocks
    UserService userService;
    ReimbursementService reimbursementService;
    User user;
    Reimbursement reimbursement;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser(){
        user = new User();
        when(userDao.createUser(user)).thenReturn(true);
        assertTrue(userService.createUser(user));
    }

    @Test
    void testIfUserExistsById() {
        user = new User(89, "Paul", "Ball", UserRole.EMPLOYEE, "paul.ball@gmail.com", "password");
        when(userDao.getUserById(89)).thenReturn(user);
        assertTrue(userService.createUser(user));
    }

    @Test
    void verifyLogin() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getAllUsers() {
    }



    @Test
    void updateUser() {
    }
}