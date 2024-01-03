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
    public int doc_ids;
    public String doc_nomer;
    public int cli_ids;
    public String cli_mname;
    public int tarifa_ids;
    public String tarifa_mname;
    public double price;
    public int vehicle_ids;
    public String vehicle_mname;
    public Date date;

    public int getDoc_ids() {
        return doc_ids;
    }

    public void setDoc_ids(int doc_ids) {
        this.doc_ids = doc_ids;
    }

    public String getDoc_nomer() {
        return doc_nomer;
    }

    public void setDoc_nomer(String doc_nomer) {
        this.doc_nomer = doc_nomer;
    }

    public int getCli_ids() {
        return cli_ids;
    }

    public void setCli_ids(int cli_ids) {
        this.cli_ids = cli_ids;
    }

    public String getCli_mname() {
        return cli_mname;
    }

    public void setCli_mname(String cli_mname) {
        this.cli_mname = cli_mname;
    }

    public int getTarifa_ids() {
        return tarifa_ids;
    }

    public void setTarifa_ids(int tarifa_ids) {
        this.tarifa_ids = tarifa_ids;
    }

    public String getTarifa_mname() {
        return tarifa_mname;
    }

    public void setTarifa_mname(String tarifa_mname) {
        this.tarifa_mname = tarifa_mname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVehicle_ids() {
        return vehicle_ids;
    }

    public void setVehicle_ids(int vehicle_ids) {
        this.vehicle_ids = vehicle_ids;
    }

    public String getVehicle_mname() {
        return vehicle_mname;
    }

    public void setVehicle_mname(String vehicle_mname) {
        this.vehicle_mname = vehicle_mname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
