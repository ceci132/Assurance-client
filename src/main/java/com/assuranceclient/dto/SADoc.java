/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assuranceclient.dto;

import java.util.Date;

/**
 *
 * @author Цветан Иванов
 */
public class SADoc {
    public int ids;
    public String nomer;
    public int ids_client;
    public int ids_price;
    public double doc_price;
    public int ids_vehicle;
    public Date date_from;
    public Date date_to;

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }
    
    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public double getDoc_price() {
        return doc_price;
    }

    public void setDoc_price(double doc_price) {
        this.doc_price = doc_price;
    }

    public int getIds_client() {
        return ids_client;
    }

    public void setIds_client(int ids_client) {
        this.ids_client = ids_client;
    }

    public int getIds_price() {
        return ids_price;
    }

    public void setIds_price(int ids_price) {
        this.ids_price = ids_price;
    }
    
    public int getIds_vehicle() {
        return ids_vehicle;
    }

    public void setIds_vehicle(int ids_vehicle) {
        this.ids_vehicle = ids_vehicle;
    }

    
}
