/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.model.Database;
import com.model.GenericList;
import com.model.item;
import com.model.orderInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class checkOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Connection con = Database.getConnection();            
            GenericList<orderInfo> orderInfo = new GenericList<>();      
            GenericList<GenericList<item>> listofitems = new GenericList<>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from orderlogs where phoneno="+request.getParameter("phoneno"));
            while(rs.next()){
                orderInfo item = new orderInfo();
                item.setOrderid(rs.getInt("orderid"));
                item.setOrderstatus(rs.getString("orderstatus"));
                item.setPhoneno(rs.getString("phoneno"));
                item.setAmount(rs.getDouble("amount"));
                orderInfo.setList(item);
            }
            HttpSession session = request.getSession();
            GenericList<item> reference = (GenericList<item>) session.getAttribute("itemList");
            PreparedStatement st1 = con.prepareStatement("select itemid,qty from orders where orderid=?");
            for(orderInfo x: orderInfo.list){
                GenericList<item> items = new GenericList<>();
                st1.setInt(1, x.getOrderid());
                ResultSet rs1 = st1.executeQuery();
                while(rs1.next()){
                    for(item i: reference.list){
                        if(i.getId().equalsIgnoreCase(rs1.getString("itemid"))){
                            i.setQty(rs1.getInt("qty"));
                            items.setList(i);
                            break;
                        }       
                    }
                }
                listofitems.setList(items);
            }
            request.setAttribute("orderDetails", listofitems);
            request.setAttribute("orderInfo", orderInfo);
            request.getRequestDispatcher("checkOrder.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(checkOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
