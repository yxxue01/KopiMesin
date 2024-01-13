/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.model;

/**
 *
 * @author hp
 */
public class processor {

    GenericList<GenericList<item>> items;
    GenericList<item> exist;

    public processor(GenericList<GenericList<item>> items) {
        this.items = items;
        exist = new GenericList<>();
    }
        
    public GenericList<item> count(){
        for(GenericList<item> x : items.getListAll()){
            for(item y:x.getListAll()){
                boolean isok = find(y);
                if(isok)
                    exist.setList(y);
            }
        }
        
        emptyQty();
        refillQty();
        for(item x: exist.getListAll()){
            x.sales = x.saleQty*x.price;
        }
        return exist;
    }
        
    private boolean find(item x){
        boolean what = true;
        for(item y:exist.getListAll()){
            if(y.getId().equals(x.getId())){
                what = false;
            }
        }
        return what;
    }
    
    private int find2(item x){
        int index = -1;
        for(item y:exist.getListAll()){
            index +=1;
            if(y.getId().equals(x.getId())){
                break;
            }
        }
        return index;
    }
    
    private void emptyQty(){
        for(item x:exist.getListAll()){
            x.saleQty=0;
        }
    }
    
    private void refillQty(){
        for(GenericList<item> x : items.getListAll()){
            for(item y:x.getListAll()){
                int index = find2(y);
                exist.getList(index).saleQty+=y.qty;
            }
        }
    }
}
