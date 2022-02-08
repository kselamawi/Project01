package com.revature.models;

public class User {
    private Integer id;
    private String f_name;
    private String l_name;
    private UserRole UserRole;
    private String email;
    private String password;

    public User(String f_name, String l_name, UserRole userRole, String email, String password) {
        this.f_name = f_name;
        this.l_name = l_name;
        UserRole = userRole;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String f_name, String l_name, UserRole userRole, String email, String password) {
        this.id = id;
        this.f_name = f_name;
        this.l_name = l_name;
        UserRole = userRole;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public UserRole getUserRole() {
        return UserRole;
    }

    public void setUserRole(UserRole userRole) {
        UserRole = userRole;
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

    public void setId(Integer id) {
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