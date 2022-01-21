package com.example.atry;

public class Cart {
    String fname,lprice;

    public Cart(){

    }

    public Cart(String Fname,String Lprice){
        this.fname = Fname;
        this.lprice = Lprice;
    }

    public String getFname() {
        return fname;
    }

    public String getLprice() {
        return lprice;
    }
}
