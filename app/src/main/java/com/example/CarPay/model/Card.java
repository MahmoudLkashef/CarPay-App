package com.example.CarPay.model;

public class Card {
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

    public Card(String cardNum, String cvc, String date, String cash) {
        this.cardNum = cardNum;
        this.cvc = cvc;
        this.date = date;
        this.cash = cash;
    }
}
