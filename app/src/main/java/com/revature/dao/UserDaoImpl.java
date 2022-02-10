package com.revature.dao;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.util.ConUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    @Override
    public boolean createUser(User user) {
        String sql = "insert into users (email, password, first_name, last_name, user_role) values (?, ?, ?, ?, ?)";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            UserRole[] types = UserRole.values();
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getF_name());
            ps.setString(4, user.getL_name());
            ps.setInt(5,  user.getUserRole().ordinal());
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("User " + user.getId() + " was created");
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "delete from users where id = ?";

        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("User " + id + " was deleted");
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update users set first_name = ?, last_name = ?, password = ?, email = ?, user_role = ? where id = ?";
        try(Connection c = ConUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
        ){


            UserRole[] types = UserRole.values();
            ps.setString(1, user.getF_name());
            ps.setString(2, user.getL_name());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setInt(5,  user.getUserRole().ordinal());
            ps.setInt(6, user.getId());


            int alteredRows = ps.executeUpdate();
            if(alteredRows == 1){
                System.out.println("User " + user.getId() + " was changed");
                return true;
            }


        } catch (SQLException e){
            e.printStackTrace();
        }



        return false;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from users";

        Connection c = null;
        try {
            c = ConUtil.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<User> userList = new ArrayList<>();

            while(rs.next()){
            User user = new User();
            int typeOrdinal = rs.getInt("user_role");
            UserRole[] types = UserRole.values();

            user.setId(rs.getInt("id"));
            user.setUserRole(types[typeOrdinal]);
            user.setEmail(rs.getString("email"));
            user.setF_name(rs.getString("first_name"));
            user.setL_name(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            userList.add(user);
            }
            return userList;

            } catch (SQLException e) {
        e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select * from users where id = ?";
        try( Connection con =ConUtil.getConnection();
             PreparedStatement ps = con.prepareCall(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setF_name(rs.getString("first_name"));
                user.setL_name(rs.getString("last_name"));
                int typeOrdinal = rs.getInt("user_role");
                UserRole[] types = UserRole.values();
                user.setUserRole(types[typeOrdinal]);
                return user;
            }
        } catch( SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
     public User getUserByEmailAndPassword(String email, String password) {
        String sql = "select * from users where email = ? and password = ?";
        try( Connection con =ConUtil.getConnection();
        PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("You are login");
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setF_name(rs.getString("first_name"));
                user.setL_name(rs.getString("last_name"));
                int typeOrdinal = rs.getInt("user_role");
                UserRole[] types = UserRole.values();
                user.setUserRole(types[typeOrdinal]);
                return user;
            }
        } catch( SQLException e){
           e.printStackTrace();
            }
        return null;
    }
}
