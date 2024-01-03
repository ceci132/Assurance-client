/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assuranceclient.dto;

/**
 *
 * @author Цветан Иванов
 */
public class Tarifa {
    
    public int ids;

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public double getCena() {
        return price;
    }

    public void setCena(double price) {
        this.price = price;
    }
    public String mname;
    public double price;    
}
