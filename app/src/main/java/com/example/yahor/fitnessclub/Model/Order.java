package com.example.yahor.fitnessclub.Model;

public class Order {
    private  String TrainerId;
    private String TrainerName;
    private String Quantity;
    private String Price;
    private String Discount;

    public Order() {
    }

    public Order(String trainerId, String trainerName, String quantity, String price, String discount) {
        TrainerId = trainerId;
        TrainerName = trainerName;
        Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    public String getTrainerId() {
        return TrainerId;
    }

    public void setTrainerId(String trainerId) {
        TrainerId = trainerId;
    }

    public String getTrainerName() {
        return TrainerName;
    }

    public void setTrainerName(String trainerName) {
        TrainerName = trainerName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
