/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import com.DAO.staffDAO;
import com.model.GenericList;
import com.model.staff;
import java.io.IOException;
import java.io.PrintWriter;
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
public class staffServlet extends HttpServlet {
    
    private staffDAO staffDAO;
    PrintWriter out;

    public void init() {
        staffDAO = new staffDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        out = response.getWriter();
        if (request.getParameter("operation") != null) {
            formOperation(request, response);
        } else {
            String path = request.getParameter("path");
            switch (path) {
                case "loadStaff":
                    loadStaff(request,response);
                    request.getRequestDispatcher("manager_staffList.jsp").forward(request, response);
                    break;
                case "loadProfile":
                    loadProfile(request,response);
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                    break;
                case "deleteStaff":
                    deleteStaff(request,response);
                    request.getRequestDispatcher("manager_staffList.jsp").forward(request, response);
                    break;
                case "deleteProfile":
                    deleteStaff(request,response);
                    request.getRequestDispatcher("manager_staffList.jsp").forward(request, response);
                    break;
                case "updateForm":
                    loadProfile(request,response);
                    request.getRequestDispatcher("editProfile.jsp").forward(request, response);
                    break;

            }
        }
    }
    
    public void formOperation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        switch (operation) {
            case "registerStaff":
                createStaff(request,response);
                request.getRequestDispatcher("registerStaff.jsp").forward(request, response);
                break;
            case "updateStaff":
                updateStaff(request,response);
                loadProfile(request,response);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
            case "changePassword":
                changePassword(request,response);
                request.getRequestDispatcher("changePass.jsp").forward(request, response);
                break;
            case "login":
                login(request,response);
                break;
        }
    }
    
    public void loadProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        staff staff = staffDAO.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("manager", staff);
    }
    
    public void loadStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        GenericList<staff> staffs = staffDAO.read();
        request.setAttribute("staffList", staffs);
    }
    
    public void deleteStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int staffid = Integer.parseInt(request.getParameter("id"));
        staffDAO.delete(staffid);
    }
    
    public void createStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String staffname = request.getParameter("staffName");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String password = request.getParameter("password");
        staffDAO.create(new staff(staffname,role,email,tel,password));
    }
    
    public void updateStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int staffid = Integer.parseInt(request.getParameter("id"));
        String tel = request.getParameter("tel");
        String role = request.getParameter("role");
        staffDAO.update(new staff(staffid,name,role,email,tel,null));
    }
    
    public void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = "123";//session.getAttribute("password");
        String curPass = request.getParameter("current_password");
        String newPass = request.getParameter("new_password");
        int staffID = Integer.parseInt(request.getParameter("staffId"));
        
        if(password.equals(curPass)){
            staffDAO.update(staffID, newPass);
        }
    }
    
    public void login(HttpServletRequest request, HttpServletResponse response)
             {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        boolean isMatch = staffDAO.login(email, password, role);
        
        if(isMatch){
            try {
                HttpSession session = request.getSession();
                staff staff = staffDAO.read(email,password,role);
                session.setAttribute("username", email);
                session.setAttribute("staffid", staff.getStaffid());
                if(staff.getRole().equalsIgnoreCase("cashier")){
                    session.setAttribute("staffname", staff.getStaffName());
                    request.getRequestDispatcher("itemServlet?operation=load").forward(request,response);
                }
                else if(staff.getRole().equalsIgnoreCase("cook")){
                    session.setAttribute("staffname", staff.getStaffName());
                    request.getRequestDispatcher("cook/orderServlet?operation=cookOrder").forward(request,response);
                }
                else if(staff.getRole().equalsIgnoreCase("manager")){
                    session.setAttribute("staffusr", staff.getEmail());
                    session.setAttribute("staffname", staff.getStaffName());
                    response.sendRedirect("manager/home.jsp");
                }
            } catch (ServletException ex) {
                out.print(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(staffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                response.sendRedirect("loginPage.jsp");
            } catch (IOException ex) {
                Logger.getLogger(staffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
