package com.revature.models;

import java.lang.reflect.Type;

public class User {
    private int id;
    private String f_name;
    private String l_name;
    private Type UserRole;
    private String email;
    private String password;

    public User(String f_name, String l_name, Type user_Role, String email, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        UserRole = user_Role;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", User_Role=" + UserRole +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Type getUserRole() {
        return UserRole;
    }

    public void setUserRole(Type userRole) {
        UserRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}