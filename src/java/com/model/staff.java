/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class staff {
    int staffid;
    String staffName;
    String role;
    String email;
    String tel;
    String password;

    public staff(String staffName, String role, String email, String tel, String password) {
        this.staffName = staffName;
        this.role = role;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    public staff(int staffid, String staffName, String role, String email, String tel, String password) {
        this.staffid = staffid;
        this.staffName = staffName;
        this.role = role;
        this.email = email;
        this.tel = tel;
        this.password = password;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
