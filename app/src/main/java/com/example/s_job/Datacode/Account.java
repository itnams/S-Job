package com.example.s_job.Datacode;

import java.io.Serializable;

public class Account implements Serializable {
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

    public Account(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDouutien() {
        return douutien;
    }

    public void setDouutien(String douutien) {
        this.douutien = douutien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Account() {
    }
}
