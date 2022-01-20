package com.example.atry;

public class Users {
    String userName,Email,Password;

    public Users() {
    }

    public Users(String userName,String Email,String Password) {

        this.userName = userName;
        this.Email = Email;
        this.Password = Password;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {return Email;}

    public void setEmail(String email){this.Email = email;}

    public String getPassword(){return Password;}
    
    public void setPassword(String password){this.Password = password;}
}
