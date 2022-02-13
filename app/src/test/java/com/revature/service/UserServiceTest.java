package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserDao ud;
    ReimbursementDao rd;


    @Test
    void testIfUserExistById() {
        assertNotNull(ud);
        when(ud.getUserById(1)).thenReturn(true);
        

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