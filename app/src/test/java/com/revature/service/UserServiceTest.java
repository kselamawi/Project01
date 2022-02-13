package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.models.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    UserDao userdao;

    @InjectMocks
    UserService userService;
    User user;
    List<User> userList;

    @Before
    public void setupTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUserByEmailAndPassword() {
        when(userService.getUserByEmailAndPassword(anyString(), anyString()))
                .thenReturn(user);

        assertEquals(userService.getUserByEmailAndPassword(anyString(), anyString()), user);
    }

    @Test
    public void createUser() {
        when(userService.createUser(any()))
                .thenReturn(true);

        assertEquals(userService.createUser(any()), true);
    }

    @Test
    public void deleteUser() {
        when(userService.deleteUser(anyInt()))
                .thenReturn(true);

        assertEquals(userService.deleteUser(anyInt()), true);
    }

    @Test
    public void getAllUsers() {
        when(userService.getAllUsers())
                .thenReturn(userList);

        assertEquals(userService.getAllUsers(), userList);
    }

    @Test
    public void getUserById() {
        when(userService.getUserById(anyInt()))
                .thenReturn(user);

        assertEquals(userService.getUserById(anyInt()), user);
    }

    @Test
    public void updateUser() {
        when(userService.updateUser(any()))
                .thenReturn(true);

        assertEquals(userService.updateUser(any()), true);
    }
}