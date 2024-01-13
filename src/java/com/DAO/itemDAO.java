/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.model.Database;
import com.model.GenericList;
import com.model.item;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class itemDAO {

    private static final String create = "insert into menu values (?,?,?,?,?);";
    private static final String read_byid = "select * from menu where itemid=?;";
    private static final String read_byorder = "select itemid,name,category,price,image,qty from menu inner join orders using (itemid) where orderid=?;";
    private static final String read_all = "select * from menu;";
    private static final String update1 = "update menu set itemid=?, name=?, category=?, price=?, image=? where itemid=?;";
    private static final String update2 = "update menu set name = ?, price = ?, category = ?, itemid = ? where itemid = ?;";
    private static final String delete = "delete from menu where itemid=?";

    public itemDAO() {
    }

    public String create(item item, InputStream is) {
        try ( Connection con = Database.getConnection();  PreparedStatement ps = con.prepareStatement(create);) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setDouble(4, item.getPrice());
            ps.setBlob(5, is);
            ps.executeUpdate();
        } catch (SQLException e) {
            return e.getMessage();
        }
        return null;
    }

    public item read(String itemid) {
        item item = new item();
        String is = "";
        try ( Connection connection = Database.getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(read_byid);) {
            preparedStatement.setString(1, itemid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("itemid");
                String itemname = rs.getString("name");
                int itemqty = rs.getInt("qty");
                double itemprice = rs.getDouble("price");
                String itemcategory = rs.getString("category");
                Blob imageData = rs.getBlob("image");
                byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                item.setId(id);
                item.setQty(itemqty);
                item.setName(itemname);
                item.setPrice(itemprice);
                item.setCategory(itemcategory);
                item.setImageData(Base64.getEncoder().encodeToString(blobData));
                item.setContent("image/jpeg");
            }

        } catch (SQLException e) {
        }
        return item;
    }

    public GenericList<item> read() {
        GenericList<item> items = new GenericList<>();
        try ( Connection connection = Database.getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(read_all);  
                ResultSet rs = preparedStatement.executeQuery();){
            while (rs.next()) {
                item newItem = new item();
                String itemid = rs.getString("itemid");
                String itemname = rs.getString("name");
                double itemprice = rs.getDouble("price");
                String itemcategory = rs.getString("category");
                Blob imageData = rs.getBlob("image");
                byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                newItem.setId(itemid);
                newItem.setQty(1);
                newItem.setName(itemname);
                newItem.setPrice(itemprice);
                newItem.setCategory(itemcategory);
                newItem.setImageData(Base64.getEncoder().encodeToString(blobData));
                newItem.setContent("image/jpeg");
                items.setList(newItem);
            }
        } catch (SQLException e) {
        }
        return items;
    }
    public GenericList<item> read(int orderid) {
        GenericList<item> items = new GenericList<>();
        try ( Connection connection = Database.getConnection();  
                PreparedStatement ps = connection.prepareStatement(read_byorder);){
                ps.setInt(1, orderid);
                ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item newItem = new item();
                String itemid = rs.getString("itemid");
                String itemname = rs.getString("name");
                double itemprice = rs.getDouble("price");
                String itemcategory = rs.getString("category");
                int qty = rs.getInt("qty");
                Blob imageData = rs.getBlob("image");
                byte[] blobData = imageData.getBytes(1, (int) imageData.length());
                newItem.setId(itemid);
                newItem.setQty(qty);
                newItem.setName(itemname);
                newItem.setPrice(itemprice);
                newItem.setCategory(itemcategory);
                newItem.setImageData(Base64.getEncoder().encodeToString(blobData));
                newItem.setContent("image/jpeg");
                items.setList(newItem);
            }
        } catch (SQLException e) {
        }
        return items;
    }
    
    public boolean delete(String itemid){
        boolean rowDeleted=false;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);){
            statement.setString(1, itemid);
            rowDeleted = statement.executeUpdate()>0;
        } catch (SQLException ex) {
        }
        return rowDeleted;
    }
    
    public boolean updateItem(item menu, InputStream is, String reference){
        boolean rowUpdated=false;
        String sql = (is!=null)?update1:update2;
        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);){
            statement.setString(1, menu.getName());
            statement.setDouble(2, menu.getPrice());
            statement.setString(3, menu.getCategory());               
            if(is!=null){
                statement.setBlob(4, is);
                statement.setString(5, menu.getId());  
                statement.setString(6, reference);  
            }else{
                statement.setString(4, menu.getId());
                statement.setString(5, reference); 
            }   
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
        }
        return rowUpdated;
    }
}
