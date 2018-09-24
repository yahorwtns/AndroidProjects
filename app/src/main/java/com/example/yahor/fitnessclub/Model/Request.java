package com.example.yahor.fitnessclub.Model;

import java.util.List;

public class Request {

    private String phone;
    private String name;
    private String adress;
    private String total;
    private String status;
    private List<Order> trainer;


    public Request() {
    }

    public Request(String phone,String name,String adress, String total, List<Order> trainer) {
        this.phone = phone;
        this.adress = adress;
        this.total = total;
        this.trainer = trainer;
        this.name = name;
        this.status = "0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getTrainer() {
        return trainer;
    }

    public void setTrainer(List<Order> trainer) {
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

