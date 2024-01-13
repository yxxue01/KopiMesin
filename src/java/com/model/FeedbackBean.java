/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Richelle Juleanne
 */
public class FeedbackBean {
    private String name;
    private String msg;
    private LocalDate date;

    public FeedbackBean(String name, String msg, Date date) {
        this.name = name;
        this.msg = msg;
        this.date = date.toLocalDate();
    }

    public FeedbackBean() {
    }

    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toLocalDate();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
}
