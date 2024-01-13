/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import com.model.item;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class order {
    int orderid;
    String orderStatus;
    String phoneNo;
    int cookid;
    Timestamp date;
    String time;
    
    ArrayList<item> order =  new ArrayList<>();

    public order(){}
    
    public order(String orderStatus, String phoneNo, int cookid) {
        this.orderStatus = orderStatus;
        this.phoneNo = phoneNo;
        this.cookid = cookid;
    }

    public String getTime() {
        LocalDateTime ref = date.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return ref.format(formatter);
    }
    
    public LocalDate getDateOnly() {
        return date.toLocalDateTime().toLocalDate();
    }
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    

    public ArrayList<item> getOrder() {
        return order;
    }

    public void setOrder(item order) {
        this.order.add(order);
    }
    
    public void setOrder(ArrayList<item> order) {
        this.order = order;
    }
    
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getCookid() {
        return cookid;
    }

    public void setCookid(int cookid) {
        this.cookid = cookid;
    }
    
    
}
