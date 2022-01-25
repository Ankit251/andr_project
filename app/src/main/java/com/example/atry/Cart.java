package com.example.atry;

public class Cart {
    String fname,lprice,imag;

    public Cart(){

    }

    public Cart(String Fname,String Lprice,String Imag){
        this.fname = Fname;
        this.lprice = Lprice;
        this.imag = Imag;
    }

    public String getFname() {
        return fname;
    }

    public String getLprice() {
        return lprice;
    }

    public String getImag() {
        return imag;
    }
}
