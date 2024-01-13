/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class GenericList<T> {
    public ArrayList<T> list;
    
    public GenericList(){
        list = new ArrayList();
    }

    public ArrayList<T> getListAll() {
        return list;
    }
    public T getList(int index) {
        return list.get(index);
    }
    public void setList(T item) {
        this.list.add(item);
    }
    public void setListAll(ArrayList<T> list) {
        this.list = list;
    }
    
    
}
