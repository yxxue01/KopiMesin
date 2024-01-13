/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.model.GenericList;
import com.model.item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class orderController extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String itemid = request.getParameter("id");
        double totalAmount = 0;
        GenericList<item> orderList = (GenericList<item>) session.getAttribute("listOrder");
        if(action.equals("add")){
            for(item x:orderList.list){
                if(x.getId().equals(itemid)){
                    x.setQty(x.getQty()+1);
                    x.setAmount(x.getPrice()*x.getQty());
                    
                }
            }
        }
        else if(action.equals("minus")){
            for(item x:orderList.list){
                if(x.getId().equals(itemid)){
                    if(x.getQty()>1){
                        x.setQty(x.getQty()-1);
                        x.setAmount(x.getPrice()*x.getQty());
                    }
                }      
            }
        }
        else if(action.equals("delete")){
            for(int x=0;x<orderList.list.size();x++){
                if(orderList.list.get(x).getId().equals(itemid)){
                    orderList.list.remove(x);
                } 
            }
        }
        for(item x: orderList.list){
            totalAmount +=x.getAmount();
        }
        session.setAttribute("totalAmount", totalAmount);
        session.setAttribute("listOrder", orderList);
        response.sendRedirect("cartPage.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
