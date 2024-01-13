/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.GenericList;
import com.model.item;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class cart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String encodedString = request.getParameter("data");
        String decoded = URLDecoder.decode(encodedString, "UTF-8");
        double totalAmount = 0;
        Gson gson = new Gson();
        ArrayList<String> itemsid = gson.fromJson(decoded, new TypeToken<ArrayList<String>>() {
        }.getType());
        HttpSession session = request.getSession();
        GenericList<item> itemList = (GenericList<item>) session.getAttribute("itemList");
        
        if (session.getAttribute("listOrder") != null) {
            GenericList<item> object = (GenericList<item>) session.getAttribute("listOrder");
            for (String x : itemsid) {
                boolean isSimilar = false;
                item item = findItem(x,itemList);
                for (item z : object.list) {
                    if (item.getId().equals(z.getId())) {
                        isSimilar = true;
                    }
                }
                if (!isSimilar) {
                    object.setList(item);
                }
            }
        } else {
            GenericList<item> orderList = new GenericList<>();
            for(String x:itemsid){
                orderList.setList(findItem(x,itemList));
            }
            session.setAttribute("listOrder", orderList);
        }
        GenericList<item> orderList = (GenericList<item>) session.getAttribute("listOrder");
        for(item x:orderList.list){
            totalAmount+=x.getAmount();
        }
        session.setAttribute("totalAmount", totalAmount);
        response.sendRedirect("cartPage.jsp");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request,response);

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
         processRequest(request,response);
    }
    
    item findItem(String id,GenericList<item> itemList){
        item result = null;
        for(item x:itemList.list){
            if(x.getId().equals(id)){
                result=x;
                break;
            }
        }
        return result;
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
