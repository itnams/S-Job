package com.example.s_job.Datacode;

public class Account {
    public String nameUser,email,passWord,phone,address, position;

    public Account(String nameUser, String email, String passWord, String phone, String address, String position) {
        this.nameUser = nameUser;
        this.email = email;
        this.passWord = passWord;
        this.phone = phone;
        this.address = address;
        this.position = position;
    }

    public Account() {
    }
}
