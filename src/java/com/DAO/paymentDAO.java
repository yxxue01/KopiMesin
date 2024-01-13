/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.model.Database;
import com.model.GenericList;
import com.model.Payment;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 *
 * @author hp
 */
public class paymentDAO {
    private static final String create = "insert into payment values (null,?,?,?,?,?,?);";
    private static final String create2 = "insert into payment values (null,?,?,?,null,?,?);";
    private static final String read_phone= "select p.paymentid, p.amount, p.date, p.method, p.receipt, p.cashierid, p.orderid from payment p inner join orderlog using (orderid) where phoneno = ?;";
    private static final String read_all = "select * from payment where DATE(date)>=? and DATE(date)<=?;";
    private static final String read_id = "select * from payment where orderid=?;";
    private static final String delete = "delete from menu where paymentid=?";

    public paymentDAO() {
    }

    public void create(Payment payment, InputStream is) {
        LocalDateTime timeStamp = LocalDateTime.now();
        try ( Connection con = Database.getConnection();  PreparedStatement ps = con.prepareStatement(create);) {
            ps.setDouble(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(timeStamp));
            ps.setString(3, payment.getMethod());
            ps.setBlob(4, is);
            ps.setInt(5, payment.getCashierid());
            ps.setInt(6, payment.getOrderid());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public void create(Payment payment) {
        LocalDateTime timeStamp = LocalDateTime.now();
        try ( Connection con = Database.getConnection();  PreparedStatement ps = con.prepareStatement(create2);) {
            ps.setDouble(1, payment.getAmount());
            ps.setTimestamp(2, Timestamp.valueOf(timeStamp));
            ps.setString(3, payment.getMethod());
            ps.setInt(4, payment.getCashierid());
            ps.setInt(5, payment.getOrderid());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public GenericList<Payment> readbyphone(String phoneno) {
        GenericList<Payment> payments = new GenericList<>();
        String is = "";
        try ( Connection connection = Database.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(read_phone);) {
            preparedStatement.setString(1, phoneno);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                int paymentid = rs.getInt("paymentid");
                double amount = rs.getDouble("amount");
                Timestamp date = rs.getTimestamp("date");
                String method = rs.getString("method");
                Blob imageData = rs.getBlob("receipt");
                byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                int cashierid = rs.getInt("cashierid");
                int orderid = rs.getInt("orderid");
                payment.setId(paymentid);
                payment.setAmount(amount);
                payment.setDate(date);
                payment.setMethod(method);
                payment.setImageData(Base64.getEncoder().encodeToString(blobData));
                payment.setContent("image/jpeg");
                payment.setCashierid(cashierid);
                payment.setOrderid(orderid);
                payments.setList(payment);
            }

        } catch (SQLException e) {
        }
        return payments;
    }

    public GenericList<Payment> readbydate(String sdate,String edate) {
        GenericList<Payment> payments = new GenericList<>();
        LocalDate sdateval = LocalDate.parse(sdate);
        LocalDate edateval = LocalDate.parse(edate);
        try ( Connection connection = Database.getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(read_all);  
                ){
            preparedStatement.setDate(1, java.sql.Date.valueOf(sdateval));
            preparedStatement.setDate(2, java.sql.Date.valueOf(edateval));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                int paymentid = rs.getInt("paymentid");
                double amount = rs.getDouble("amount");
                Timestamp date = rs.getTimestamp("date");
                String method = rs.getString("method");
                if("online".equals(method)){
                    Blob imageData = rs.getBlob("receipt");
                    byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                    payment.setImageData(Base64.getEncoder().encodeToString(blobData));
                    payment.setContent("image/jpeg");
                }
                payment.setId(paymentid);
                payment.setAmount(amount);
                payment.setDate(date);
                payment.setMethod(method);
                payments.setList(payment);
            }
        } catch (SQLException e) {
        }
        return payments;
    }
    
    public Payment readbyid(int reference) {
        Payment payment = new Payment();
        String message = null;
        try ( Connection connection = Database.getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(read_id);  
                ){
            preparedStatement.setInt(1, reference);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int paymentid = rs.getInt("paymentid");
                double amount = rs.getDouble("amount");
                Timestamp date = rs.getTimestamp("date");
                String method = rs.getString("method");
                if("online".equals(method)){
                Blob imageData = rs.getBlob("receipt");
                byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                payment.setImageData(Base64.getEncoder().encodeToString(blobData));
                payment.setContent("image/jpeg");
                }
                payment.setId(paymentid);
                payment.setAmount(amount);
                payment.setDate(date);
                payment.setMethod(method);
            }
        } catch (SQLException e) {
        }
        return payment;
    }
    
    public boolean delete(int paymentid){
        boolean rowDeleted=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);){
            statement.setInt(1, paymentid);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException ex) {
        }
        return rowDeleted;
    }
    
}
