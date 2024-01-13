/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.model.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.model.FeedbackBean;
import com.model.FeedbackBean;
import com.model.GenericList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDao {
    public String saveFeedback(FeedbackBean feedbackBean){
        String name = feedbackBean.getName();
        String msg = feedbackBean.getMsg();
        LocalDate timeStamp = LocalDate.now();
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Database.getConnection();
            String query = "insert into feedback(no,name, msg, date) values (null,?, ?, ?)";
            
            ps = con.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2, msg);
            ps.setDate(3, Date.valueOf(timeStamp));
            int i = ps.executeUpdate();
            if (i!=0)
                return "SUCCESS";
            
        }
        catch(SQLException e){
        }
        return "Oops... Something went wrong there..!";
    }
    
    public GenericList<FeedbackBean> getFeedback(){
        Connection con = null;
        PreparedStatement ps = null;
        GenericList<FeedbackBean> feedbacks = new GenericList<FeedbackBean>();
        ResultSet object = null;
        try{
            con = Database.getConnection();
            String query = "select * from feedback";
            
            ps = con.prepareStatement(query);
            object = ps.executeQuery();
            while(object.next()){
                feedbacks.setList(new FeedbackBean(object.getString("name"),object.getString("msg"),object.getDate("date")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feedbacks;
    }
}
