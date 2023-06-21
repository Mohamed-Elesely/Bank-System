// UserModel.java

package com.example.banksystem;

public class UserModel {
    private String cardNumber;
    private String name;
    private String email;
    private String password;
    private String pinKey;
    private double balance;

    public UserModel(String cardNumber, String name, String email, String password, String pinKey, double balance) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.pinKey = pinKey;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
