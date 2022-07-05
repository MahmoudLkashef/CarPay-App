package com.example.CarPay.model;

public class CustomerPayment {
    String customerName,totalPrice,carName,carModel,carPrice;

    public CustomerPayment(String name, String totalPrice, String carName, String carModel, String carPrice) {
        this.customerName = name;
        this.totalPrice = totalPrice;
        this.carName = carName;
        this.carModel = carModel;
        this.carPrice = carPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }
}
