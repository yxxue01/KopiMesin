/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.DAO.itemDAO;
import com.DAO.orderDAO;
import com.DAO.paymentDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.GenericList;
import com.model.Payment;
import com.model.closeSaleCalculator;
import com.model.item;
import com.model.order;
import com.model.processor;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
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

public class orderServlet extends HttpServlet {

    private orderDAO orderDAO;
    private paymentDAO paymentDAO;
    private itemDAO itemDAO;
    PrintWriter out;

    public void init() {
        orderDAO = new orderDAO();
        paymentDAO = new paymentDAO();
        itemDAO = new itemDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("operation") != null) {
            formOperation(request, response);
        } else {
            String path = request.getParameter("path");
            switch (path) {
                case "newOrder":
                    loadOrder(request, response, "Queue");
                    break;
                case "inprocessOrder":
                    loadOrder(request, response, "Preparing");
                    break;
                case "orderdetail":
                    orderDetail(request, response, request.getParameter("orderid"), request.getParameter("status"));
                    break;
                case "approve":
                    approvePayment(request, response, request.getParameter("orderid"));
                    break;
                case "finish":
                    finishOrder(request, response, request.getParameter("orderid"));
                    break;
                case "cancel":
                    cancelOrder(request, response, request.getParameter("orderid"));
                    break;
                case "serve":
                    serveOrder(request, response, request.getParameter("orderid"));
                    break;
                case "closeSales":
                    closeSales(request,response);
                    break;
                case "cookOrder":
                    cookOrder(request,response);
                    request.getRequestDispatcher("itemServlet?path=cookOrder").forward(request, response);
                    break;
                
            }
        }
    }

    public void formOperation(HttpServletRequest request, HttpServletResponse response)
            {
        try {
            String operation = request.getParameter("operation");
            switch (operation) {
                case "addOrder":
                    addOrder(request, response);
                    break;
                case "addOrder2":
                    addOrder2(request, response);
                    break;
                case "orderHistory":
                    loadOrderHistory(request, response);
                    break;
                case "sales":
                    loadOrderSales(request, response);
                    break;
                case "checkOrder":
                    checkOrder(request,response);
                    request.getRequestDispatcher("itemServlet").forward(request, response);
                    break;
                case "cookOrder":
                    cookOrder(request,response);
                    request.getRequestDispatcher("itemServlet?operation=cookOrder").forward(request, response);
                    break;
            }
        } catch (ServletException ex) {
            out.print("haha");
        } catch (IOException ex) {
            out.print("hihi");
        }
    }

    public <T> void loadOrder(HttpServletRequest request, HttpServletResponse response, T info)
            throws ServletException, IOException {
        GenericList<order> object = orderDAO.read(info, "status");
        request.setAttribute("orderList", object);
        if ("Queue".equals((String) info)) {
            request.getRequestDispatcher("manageOrder_1.jsp").forward(request, response);
        } else if ("Preparing".equals((String) info)) {
            request.getRequestDispatcher("manageOrder_2.jsp").forward(request, response);
        }
    }

    public void loadOrderHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sdate = request.getParameter("sdate");
        String edate = request.getParameter("edate");
        String phoneno = request.getParameter("phoneno");
        GenericList<order> object = orderDAO.readbyrange(sdate, edate, phoneno);
        request.setAttribute("orderList", object);
        request.setAttribute("rsdate", sdate);
        request.setAttribute("redate", edate);
        request.getRequestDispatcher("manageOrder_3.jsp").forward(request, response);
    }
    
    public void loadOrderSales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sdate = request.getParameter("sdate");
        String edate = request.getParameter("edate");
        GenericList<order> object = orderDAO.readbyrange(sdate, edate, "0");
        GenericList<GenericList<item>> items = new GenericList<>();
        for(order x:object.getListAll()){
            items.setList(itemDAO.read(x.getOrderid()));
        }
        processor x = new processor(items);
        GenericList<item> result = x.count();
        double amount =0;
        for(item u:result.getListAll()){
            amount +=u.getSales();
        }
        request.setAttribute("salesList", result);
        request.setAttribute("salesAmount", amount);
        request.getRequestDispatcher("salesReport.jsp").forward(request, response);
    }

    public void orderDetail(HttpServletRequest request, HttpServletResponse response, String orderid, String status)
            throws ServletException, IOException {
        GenericList<order> object = null;
        int id = Integer.parseInt(orderid);
        if ("nostatus".equals(status)) {
            String sdate = request.getParameter("sdate");
            String edate = request.getParameter("edate");
            object = orderDAO.readbyrange(sdate, edate);
            request.setAttribute("rsdate", sdate);
            request.setAttribute("redate", edate);
        } else {
            object = orderDAO.read(status, "status");
        }
        GenericList<order> order = orderDAO.read(id, "id");
        Payment payment = paymentDAO.readbyid(id);
        out.print(payment.getAmount());
        request.setAttribute("orderList", object);
        request.setAttribute("order", order);
        request.setAttribute("payment", payment);
        request.getRequestDispatcher("itemServlet?path=orderdetail&status=" + status).forward(request, response);
    }

    public void approvePayment(HttpServletRequest request, HttpServletResponse response, String orderid)
            throws ServletException, IOException {
        int id = Integer.parseInt(orderid);
        orderDAO.updateItem("Preparing", id);
        loadOrder(request, response, "Queue");
    }
    
    public void serveOrder(HttpServletRequest request, HttpServletResponse response, String orderid)
            throws ServletException, IOException {
        int id = Integer.parseInt(orderid);
        orderDAO.updateItem("Served", id);
        loadOrder(request, response, "Preparing");
    }
    
    public void finishOrder(HttpServletRequest request, HttpServletResponse response, String orderid)
            throws ServletException, IOException {
        int id = Integer.parseInt(orderid);
        orderDAO.updateItem("Finished", id);
        cookOrder(request,response);
        request.getRequestDispatcher("itemServlet?path=cookOrder").forward(request, response);
    }
    
    public void cancelOrder(HttpServletRequest request, HttpServletResponse response, String orderid)
            throws ServletException, IOException {
        int id = Integer.parseInt(orderid);
        orderDAO.delete(id);
        cookOrder(request,response);
        request.getRequestDispatcher("itemServlet?path=cookOrder").forward(request, response);
    }

    public void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String orderData = request.getParameter("orderData");
        Gson gson = new Gson();
        ArrayList<item> items = gson.fromJson(orderData, new TypeToken<ArrayList<item>>() {
        }.getType());
        HttpSession session = request.getSession();
        GenericList<order> orderList;
        if (session.getAttribute("orderList") != null) {
            orderList = (GenericList<order>) session.getAttribute("orderList");
        } else {
            orderList = new GenericList<>();
        }

        String phoneno = request.getParameter("phoneno");
        int cookid = Integer.parseInt(request.getParameter("cookid"));
        order currentOrder = new order("Queue", phoneno, cookid);
        int orderid = orderDAO.create(currentOrder);
        orderDAO.attachItem(items, orderid);
        addPayment(request, response, orderid);
        response.sendRedirect(request.getContextPath() + "/takeOrder.jsp");
    }
    
    public void addOrder2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        GenericList<item> orderList = (GenericList<item>) session.getAttribute("listOrder");
        String phoneno = request.getParameter("phoneno");
        int cookid = 1108;
        order currentOrder = new order("Queue", phoneno, cookid);
        int orderid = orderDAO.create(currentOrder);
        orderDAO.attachItem(orderList.getListAll(), orderid);
        addPayment(request, response, orderid);
        response.sendRedirect("orderPage.jsp");
    }

    private void addPayment(HttpServletRequest request, HttpServletResponse response, int orderid)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String method = request.getParameter("method");
        String orderAmount = request.getParameter("orderAmount");
        int cashierid = (Integer)session.getAttribute("staffid");
        Part part = null;
        InputStream is = null;
        Payment payment = null;
        if (method.equals("online")) {
            try {
                part = request.getPart("online");
                is = part.getInputStream();
                paymentDAO.create(payment);
            } catch (IOException ex) {
                Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException ex) {
                Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (method.equals("cash")) {
            payment = new Payment(Double.parseDouble(orderAmount),
                    method, cashierid, orderid);
            paymentDAO.create(payment, is);
        }
    }
    
    public void closeSales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericList<Payment> payments = paymentDAO.readbydate(LocalDate.now().toString(), LocalDate.now().toString());
        closeSaleCalculator object = new closeSaleCalculator(payments);
        double onlineTotal = object.getOnlinePayment();
        double cashTotal = object.getCashPayment();
        double total = object.getTotalPayment();
        String date = LocalDate.now().toString();
        request.setAttribute("onlineTotal", onlineTotal);
        request.setAttribute("cashTotal", cashTotal);
        request.setAttribute("total", total);
        request.setAttribute("date", date);
        request.getRequestDispatcher("salesPage.jsp").forward(request, response);
    }
    
    public void cookOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericList<order> orders = orderDAO.read("Preparing","status");
        request.setAttribute("orders", orders);
    }
    
    public void checkOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericList<order> orders = orderDAO.read(request.getParameter("phoneno"),"phone");
        request.setAttribute("orders", orders);
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
