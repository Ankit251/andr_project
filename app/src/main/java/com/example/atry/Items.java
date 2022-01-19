package com.example.atry;

public class Items {
    String firstName, offerprice, age, userName,pid;

    public Items() {
    }

    public Items(String firstName, String offerprice, String age, String userName,String pid) {
        this.firstName = firstName;
        this.offerprice = offerprice;
        this.age = age;
        this.userName = userName;
        this.pid = pid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return offerprice;
    }

    public void setLastName(String offerprice) {
        this.offerprice = offerprice;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
