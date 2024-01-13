/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.model.Database;
import com.model.GenericList;
import com.model.item;
import com.model.order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class orderDAO {
    
    private static final String create = "insert into orderlog values (null,?,?,?,?);";
    private static final String read_phone= "select * from orderlog where phoneno=?;";
    private static final String read_range= "select * from orderlog where DATE(date)>=? and DATE(date)<=?";
    private static final String read_status = "select * from orderlog where orderstatus=? and DATE(date)=?;";
    private static final String read_id = "select * from orderlog where orderid=?;";
    private static final String update = "update orderlog set orderstatus=? where orderid=?;";
    private static final String delete = "delete from orderlog where orderid=?";
    
    public orderDAO(){}
    
    public int create(order order) {
        LocalDateTime timeStamp = LocalDateTime.now();
        int orderid= 0;
        try ( Connection con = Database.getConnection();  PreparedStatement ps = con.prepareStatement(create);
                PreparedStatement ps1 = con.prepareStatement("select orderid from orderlog where phoneno = ? and date = ?;");) {
            ps.setString(1, order.getOrderStatus());
            ps.setString(2, order.getPhoneNo());
            ps.setInt(3, order.getCookid());
            ps.setTimestamp(4, Timestamp.valueOf(timeStamp));
            ps.executeUpdate();
            ps1.setString(1, order.getPhoneNo());
            ps1.setTimestamp(2, Timestamp.valueOf(timeStamp));
            ResultSet rs = ps1.executeQuery();
            while(rs.next())
                orderid = rs.getInt("orderid");
        } catch (SQLException e) {
//            orderid = e.getMessage();
        }
        return orderid;
    }
    
    public void attachItem(List<item> items,int orderid){
        String query = "insert into orders values ";
            for (int i = 0; i < items.size(); i++) {
                item x = items.get(i);
                query = query.concat("(null," + orderid + ",'" + x.getId() + "'," + x.getQty() + ")");
                if (i == items.size() - 1) {
                    query = query.concat(";");
                } else {
                    query = query.concat(",");
                }
            }
        try(Connection con = Database.getConnection();
            Statement st = con.createStatement();){
            st.executeUpdate(query);
        }catch (SQLException ex) {
        }
    }
    public <T> GenericList<order> read(T reference,String type) {
        GenericList<order> orders = new GenericList<>();
        String sql = (type.equals("phone"))? read_phone:(type.equals("status"))? read_status:read_id;
        try ( Connection connection = Database.getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            if(type.equals("phone"))
                preparedStatement.setString(1, (String) reference);
            else if(type.equals("status")){
                LocalDateTime current = LocalDateTime.now();
                preparedStatement.setString(1, (String) reference);
                preparedStatement.setDate(2, java.sql.Date.valueOf(current.toLocalDate()));
            }
            else if(type.equals("id")){
                preparedStatement.setInt(1, (Integer) reference);
            }
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                order order = new order();
                int id = rs.getInt("orderid");
                String status = rs.getString("orderstatus");
                int cookid = rs.getInt("cookid");
                Timestamp date = rs.getTimestamp("date");
                order.setOrderid(id);
                order.setOrderStatus(status);
                order.setCookid(cookid);
                order.setPhoneNo(rs.getString("phoneno"));
                order.setDate(date);
                orders.setList(order);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
    public GenericList<order> readbyrange(String sdate,String edate,String phoneno) {
        GenericList<order> orders = new GenericList<>();
        String sql = read_range;
        LocalDate sdateval = LocalDate.parse(sdate);
        LocalDate edateval = LocalDate.parse(edate);
        if(phoneno.length()>1){
            sql=sql.concat("and phoneno=?");
        }
        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(sql);) {
            if(phoneno.length()>1){
                ps.setDate(1, java.sql.Date.valueOf(sdateval));
                ps.setDate(2, java.sql.Date.valueOf(edateval));
                ps.setString(3, phoneno);
            }
            else{
                ps.setDate(1, java.sql.Date.valueOf(sdateval));
                ps.setDate(2, java.sql.Date.valueOf(edateval));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order order = new order();
                int orderid = rs.getInt("orderid");
                String status = rs.getString("orderstatus");
                int cookid = rs.getInt("cookid");
                Timestamp date = rs.getTimestamp("date");
                order.setOrderid(orderid);
                order.setOrderStatus(status);
                order.setCookid(cookid);
                order.setPhoneNo(rs.getString("phoneno"));
                order.setDate(date);
                orders.setList(order);
            }
        } catch (SQLException ex) {
        }
        return orders;
    }
    public GenericList<order> readbyrange(String sdate,String edate) {
        GenericList<order> orders = new GenericList<>();
        String sql = read_range;
        LocalDate sdateval = LocalDate.parse(sdate);
        LocalDate edateval = LocalDate.parse(edate);

        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(sql);) {
                ps.setDate(1, java.sql.Date.valueOf(sdateval));
                ps.setDate(2, java.sql.Date.valueOf(edateval));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                order order = new order();
                int orderid = rs.getInt("orderid");
                String status = rs.getString("orderstatus");
                int cookid = rs.getInt("cookid");
                Timestamp date = rs.getTimestamp("date");
                order.setOrderid(orderid);
                order.setOrderStatus(status);
                order.setCookid(cookid);
                order.setPhoneNo(rs.getString("phoneno"));
                order.setDate(date);
                orders.setList(order);
            }
        } catch (SQLException ex) {
        }
        return orders;
    }
    
    public boolean delete(int orderid){
        boolean rowDeleted=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);){
            statement.setInt(1, orderid);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException ex) {
        }
        return rowDeleted;
    }
    
    public boolean updateItem(String status,int orderid){
        boolean rowUpdated=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(update);){
            statement.setString(1, status);
            statement.setInt(2, orderid);
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
        }
        return rowUpdated;
    }
}
