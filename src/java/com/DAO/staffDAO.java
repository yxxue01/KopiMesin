/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.model.Database;
import com.model.GenericList;
import com.model.staff;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class staffDAO {
    
    private static final String create = "insert into staff values (null,?,?,?,?,?);";
    private static final String read_all = "select * from staff where role <> ?;";
    private static final String read_id = "select * from staff where staffID=?;";
    private static final String read_login = "select * from staff where email=? and password=? and role=?";
    private static final String update_password_manager= "update staff set password=? where staffID=?;";
    private static final String update_all= "update staff set staffName=?,role=?,email=?,tel=? where staffID=?;";
    private static final String delete = "delete from staff where staffID=?;";
    
    public staffDAO() {
    }
    
    public void create(staff staff) {
        try ( Connection con = Database.getConnection();  PreparedStatement ps = con.prepareStatement(create);) {
            ps.setString(1, staff.getStaffName());
            ps.setString(2, staff.getRole());
            ps.setString(3, staff.getEmail());
            ps.setString(4, staff.getTel());
            ps.setString(5, staff.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public GenericList<staff> read(){
        GenericList<staff> staffs = new GenericList<>();
        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(read_all);  
                ){
            ps.setString(1,"Manager");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int staffid = rs.getInt("staffID");
                String staffname = rs.getString("staffName");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String password = rs.getString("password");
                staffs.setList(new staff(staffid,staffname,role,email,tel,password));
            }
        } catch (SQLException e) {
        }
        return staffs;
    }
    
    public staff read(int id){
        staff staff = null;
        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(read_id);  
                ){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int staffid = rs.getInt("staffID");
                String staffname = rs.getString("staffName");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String password = rs.getString("password");
                staff = new staff(staffid,staffname,role,email,tel,password);
            }
        } catch (SQLException e) {
        }
        return staff;
    }
    
    public staff read(String x,String y,String z){
        staff staff = null;
        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(read_login);  
                ){
            ps.setString(1,x);
            ps.setString(2,y);
            ps.setString(3,z);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int staffid = rs.getInt("staffID");
                String staffname = rs.getString("staffName");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String password = rs.getString("password");
                staff = new staff(staffid,staffname,role,email,tel,password);
            }
        } catch (SQLException e) {
        }
        return staff;
    }
    
    public boolean delete(int staffid){
        boolean rowDeleted=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);){
            statement.setInt(1, staffid);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException ex) {
        }
        return rowDeleted;
    }

    public boolean update(staff staff){
        boolean rowUpdated=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(update_all);){
            statement.setString(1, staff.getStaffName());
            statement.setString(2, staff.getRole());
            statement.setString(3, staff.getEmail());
            statement.setString(4, staff.getTel());
            statement.setInt(5, staff.getStaffid());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
        }
        return rowUpdated;
    }
    
    public boolean update(int staffid,String password){
        boolean rowUpdated=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(update_password_manager);){
            statement.setString(1, password);
            statement.setInt(2, staffid);
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
        }
        return rowUpdated;
    }
    
    public boolean login(String email,String password,String role){
        boolean isCorrect = false;
        try(Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(read_login)){
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);
            isCorrect = ps.execute();
        } catch (SQLException ex) {
        }
        return isCorrect;
    }
    
}
