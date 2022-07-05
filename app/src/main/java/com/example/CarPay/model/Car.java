package com.example.CarPay.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    String name , modelYear,price ;
    Bitmap img;
    String speed,gas,motorCapacity,horsePower,seats,type,country;

    public Car() {
    }

    protected Car(Parcel in) {
        name = in.readString();
        modelYear = in.readString();
        price = in.readString();
        speed = in.readString();
        gas = in.readString();
        motorCapacity = in.readString();
        horsePower = in.readString();
        seats = in.readString();
        type = in.readString();
        country = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Car(String name, String modelYear, String price) {
        this.name = name;
        this.modelYear = modelYear;
        this.price = price;
    }

    public Car(String name, String modelYear, String price, Bitmap img, String speed, String gas, String motorCapacity, String horsePower, String seats, String type, String country) {
        this.name = name;
        this.modelYear = modelYear;
        this.price = price;
        this.img = img;
        this.speed = speed;
        this.gas = gas;
        this.motorCapacity = motorCapacity;
        this.horsePower = horsePower;
        this.seats = seats;
        this.type = type;
        this.country=country;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Car(String name, String modelYear, String price, String speed, String gas, String motorCapacity, String horsePower, String seats, String type) {
        this.name = name;
        this.modelYear = modelYear;
        this.price = price;
        this.speed = speed;
        this.gas = gas;
        this.motorCapacity = motorCapacity;
        this.horsePower = horsePower;
        this.seats = seats;
        this.type = type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getMotorCapacity() {
        return motorCapacity;
    }

    public void setMotorCapacity(String motorCapacity) {
        this.motorCapacity = motorCapacity;
    }

    public String getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(String horsePower) {
        this.horsePower = horsePower;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(modelYear);
        parcel.writeString(price);
        parcel.writeString(speed);
        parcel.writeString(gas);
        parcel.writeString(motorCapacity);
        parcel.writeString(horsePower);
        parcel.writeString(seats);
        parcel.writeString(type);
        parcel.writeString(country);
    }
}
