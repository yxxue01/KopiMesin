/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.sql.Timestamp;


/**
 *
 * @author hp
 */
public class Payment {
    int id;
    double amount;
    Timestamp date;
    String method;
    String imageData;
    String content;
    int cashierid;
    int orderid;
    
    public Payment(){}

    public Payment(double amount, String method, int cashierid, int orderid) {
        this.amount = amount;
        this.method = method;
        this.cashierid = cashierid;
        this.orderid = orderid;
    }
    
    
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public int getCashierid() {
        return cashierid;
    }

    public void setCashierid(int cashierid) {
        this.cashierid = cashierid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    
    
}
