/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.DAO.itemDAO;
import com.model.GenericList;
import com.model.Payment;
import com.model.item;
import com.model.order;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177216)
public class itemServlet extends HttpServlet {

    private itemDAO itemDAO;
    PrintWriter out;

    public void init() {
        itemDAO = new itemDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
             {
        out = response.getWriter();
        if (request.getParameter("operation") != null) {
            try {
                formOperation(request, response);
            } catch (IOException ex) {
                Logger.getLogger(itemServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                String path = request.getParameter("path");
                switch (path) {
                    case "load":
                        loadResource(request, response);
                        response.sendRedirect("takeOrder.jsp");
                        break;
                    case "loadCust":
                        loadResource(request, response);
                        response.sendRedirect("orderPage.jsp");
                        break;
                    case "orderdetail":
                        orderDetail(request, response, request.getParameter("status"));
                        break;
                    case "delete":
                        deleteItem(request, response);
                        loadResource(request, response);
                        response.sendRedirect("menu.jsp");
                        break;
                    case "itemForm":
                        itemForm(request, response);
                        loadResource(request, response);
                        response.sendRedirect("menu.jsp");
                        break;
                    case "loadForm":
                        request.setAttribute("action", "newForm");
                        request.getRequestDispatcher("menu.jsp").forward(request, response);
                        break;
                    case "cookOrder":
                        cookOrder(request,response);
                        request.getRequestDispatcher("cook/orderList.jsp").forward(request, response);
                        break;
                }
            } catch (ServletException ex) {
                out.print(ex.getMessage());
            } catch (IOException ex) {
                out.print(ex.getMessage());
            }
        }
    }

    public void formOperation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String operation = request.getParameter("operation");
            switch (operation) {
                case "updateItem":
                    updateItem(request, response);
                    loadResource(request, response);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                    break;
                case "createItem":
                    createItem(request, response);
                    loadResource(request, response);
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                    break;
                case "checkOrder":
                    checkOrder(request,response);
                    request.getRequestDispatcher("checkOrder.jsp").forward(request, response);
                    break;
                case "load":
                        loadResource(request, response);
                        response.sendRedirect("takeOrder.jsp");
                        break;    
                        
                case "cookOrder":
                        cookOrder(request,response);
                        request.getRequestDispatcher("orderList.jsp").forward(request, response);
                        break;
            }
        } catch (ServletException ex) {
            out.print(ex.getMessage());
        }
    }

    public void loadResource(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        GenericList<item> itemList = itemDAO.read();
        session.setAttribute("itemList", itemList);
    }

    public void orderDetail(HttpServletRequest request, HttpServletResponse response, String status)
            throws ServletException, IOException {
        GenericList<order> order = (GenericList<order>) request.getAttribute("order");
        GenericList<order> orderList = (GenericList<order>) request.getAttribute("orderList");
        Payment payment = (Payment) request.getAttribute("payment");
        int orderid = order.list.get(0).getOrderid();
        GenericList<item> item = itemDAO.read(orderid);
        request.setAttribute("payment", payment);
        request.setAttribute("order", order);
        request.setAttribute("itemList", item);
        request.setAttribute("orderList", orderList);
        if ("Queue".equals(status)) {
            request.getRequestDispatcher("manageOrder_1.jsp").forward(request, response);
        } else if ("Preparing".equals(status)) {
            request.getRequestDispatcher("manageOrder_2.jsp").forward(request, response);
        } else if ("nostatus".equals(status)) {
            request.getRequestDispatcher("manageOrder_3.jsp").forward(request, response);
        }
    }

    public void itemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        item item = null;
        String itemid = request.getParameter("itemid");
        GenericList<item> items = (GenericList<item>) session.getAttribute("itemList");
        for (item x : items.getListAll()) {
            if (x.getId().equals(itemid)) {
                item = x;
            }
        }
        request.setAttribute("item", item);
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }

    public void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        item newItem = new item();
        Part part = null;
        InputStream is = null;
        if (part != null && part.getSize() > 0) {
            part = request.getPart("image");
            is = part.getInputStream();
        }
        String itemid = request.getParameter("itemid");
        String itemname = request.getParameter("itemname");
        double itemprice = Double.parseDouble(request.getParameter("itemprice"));
        String itemcategory = request.getParameter("itemcategory");

        newItem.setId(itemid);
        newItem.setName(itemname);
        newItem.setPrice(itemprice);
        newItem.setCategory(itemcategory);
        itemDAO.updateItem(newItem, is, request.getParameter("reference"));
    }

    public void createItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        item newItem = new item();
        Part part = null;
        InputStream is = null;
        part = request.getPart("image");
        is = part.getInputStream();
        String itemid = request.getParameter("itemid");
        String itemname = request.getParameter("itemname");
        double itemprice = Double.parseDouble(request.getParameter("itemprice"));
        String itemcategory = request.getParameter("itemcategory");
        newItem.setId(itemid);
        newItem.setName(itemname);
        newItem.setPrice(itemprice);
        newItem.setCategory(itemcategory);
        String message = itemDAO.create(newItem, is);
        out.print(message);
    }

    public void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemid = request.getParameter("itemid");
        itemDAO.delete(itemid);
    }

    public void cookOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericList<GenericList<item>> allItemList = new GenericList<>();
        GenericList<order> orders = (GenericList<order>) request.getAttribute("orders");
        for(order x: orders.list){
            out.print("here"+x.getOrderid());
            GenericList<item> items = itemDAO.read(x.getOrderid());
            allItemList.setList(items);
            request.setAttribute("allItemList", allItemList);
            request.setAttribute("order", orders);
        }
    }
    
    public void checkOrder(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        GenericList<GenericList<item>> allItemList = new GenericList<>();
        GenericList<order> orders = (GenericList<order>) request.getAttribute("orders");
        for(order x: orders.list){
            GenericList<item> items = itemDAO.read(x.getOrderid());
            allItemList.setList(items);
        }
        request.setAttribute("allItemList", allItemList);
        request.setAttribute("order", orders);
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
