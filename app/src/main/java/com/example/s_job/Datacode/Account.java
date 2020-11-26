package com.example.s_job.Datacode;

public class Account {
    public String nameUser,email,passWord,phone,address, position, douutien, trangthai;

    public Account(String nameUser, String email, String passWord, String phone, String address, String position, String douutien, String trangthai) {
        this.nameUser = nameUser;
        this.email = email;
        this.passWord = passWord;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.douutien = douutien;
        this.trangthai = trangthai;
    }

    public Account() {
    }
}
