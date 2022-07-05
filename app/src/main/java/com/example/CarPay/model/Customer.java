package com.example.CarPay.model;

import android.graphics.Bitmap;

public class Customer {
    String customerName,customerEmail,customerPassword;
    String carName,carModel,carPrice,speed,horsePower,motorCapacity,gas,seats,type,country;
    Bitmap image;
    String cardNum,cvc,date,cash;

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public Customer(String customerName, String customerEmail, String customerPassword, String carName, String carModel, String carPrice, String speed, String horsePower, String motorCapacity, String gas, String seats, String type, String country, Bitmap image, String cardNum, String cvc, String date, String cash) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.carName = carName;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.speed = speed;
        this.horsePower = horsePower;
        this.motorCapacity = motorCapacity;
        this.gas = gas;
        this.seats = seats;
        this.type = type;
        this.country = country;
        this.image = image;
        this.cardNum = cardNum;
        this.cvc = cvc;
        this.date = date;
        this.cash = cash;
    }

    public Customer() {
    }

    public Customer(String customerName, String customerEmail, String customerPassword, String carName, String carModel, String carPrice, String speed, String horsePower, String motorCapacity, String gas, String seats, String type, String country, Bitmap image) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.carName = carName;
        this.carModel = carModel;
        this.carPrice = carPrice;
        this.speed = speed;
        this.horsePower = horsePower;
        this.motorCapacity = motorCapacity;
        this.gas = gas;
        this.seats = seats;
        this.type = type;
        this.country = country;
        this.image = image;
    }

    public Customer(String customerName, String customerEmail, String customerPassword) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
    }

    public String getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(String motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }
}
