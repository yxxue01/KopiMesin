/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class closeSaleCalculator {
    private GenericList<Payment> payments;

    public closeSaleCalculator(GenericList<Payment> payments) {
        this.payments = payments;
    }
    
    public double getCashPayment(){
        double amount = 0;
        for(Payment x:payments.getListAll()){
            if("cash".equals(x.method))
                amount+=x.getAmount();
        }
        return amount;
    }
    public double getOnlinePayment(){
        double amount = 0;
        for(Payment x:payments.getListAll()){
            if("online".equals(x.method))
                amount+=x.getAmount();
        }
        return amount;
    }
    
    public double getTotalPayment(){
        double amount = 0;
        for(Payment x:payments.getListAll()){
            amount+=x.getAmount();
        }
        return amount;
    }
}
