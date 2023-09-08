/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anhvu.spring.dao;

import com.anhvu.spring.entity.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public class UsersDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Users> getListUsers() {
        List<Users> list = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM users";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users e = new Users(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getString("display_name"), rs.getString("address"), rs.getBoolean("roles"));
                list.add(e);
            }

        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Users getUsersById(int id) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "SELECT * FROM users WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(rs.getInt("id"), rs.getString("user"), rs.getString("password"), rs.getString("display_name"), rs.getString("address"), rs.getBoolean("roles"));
            }
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean insertUser(Users user) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "INSERT INTO users(user,password,display_name,address,roles) VALUES (?,?,?,?,?)";
            ps = con.prepareCall(query);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDisplayName());
            ps.setString(4, user.getAddress());
            ps.setBoolean(5, user.getRoles());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean updateUser(Users user) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "UPDATE users SET user=?,password=?,display_name=?,address=?,roles=? WHERE id =?";
            ps = con.prepareCall(query);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getDisplayName());
            ps.setString(4, user.getAddress());
            ps.setBoolean(5, user.getRoles());
            ps.setInt(6, user.getId());
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }

    public boolean deleteUser(int id) {
        boolean index = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diamon_shop", "root", "");
            String query = "DELETE FROM users WHERE id =?";
            ps = con.prepareCall(query);
            ps.setInt(1, id);
            index = ps.execute();
        } catch (SQLException e) {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return index;
    }
}
