/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.FeedbackBean;
import com.DAO.FeedbackDao;
import com.model.GenericList;
/**
 *
 * @author Richelle Juleanne
 */
public class FeedbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public FeedbackServlet(){}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String msg = request.getParameter("msg");
        
        FeedbackBean feedbackBean = new FeedbackBean();
        
        feedbackBean.setName(name);
        feedbackBean.setMsg(msg);
        
        FeedbackDao feedbackDao = new FeedbackDao();
        
        String feedbackSaved = feedbackDao.saveFeedback(feedbackBean);
        out.print(feedbackSaved);
        if(feedbackSaved.equals("SUCCESS")){
            request.getRequestDispatcher("customer-successFeedback.jsp").forward(request, response);
        }
        else{
            request.setAttribute("error", feedbackSaved);
            request.getRequestDispatcher("customer-feedback.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        FeedbackDao feedbackDao = new FeedbackDao();

        GenericList<FeedbackBean> objects = feedbackDao.getFeedback();
        request.setAttribute("list", objects);
        request.getRequestDispatcher("feedbacklist.jsp").forward(request, response);
    }
        
}
