package com.example.s_job.Datacode;

public class Account {
    public String nameUser,email,passWord,confirmPassword,address, position;

    public Account(String nameUser, String email, String passWord, String confirmPassword, String address, String position) {
        this.nameUser = nameUser;
        this.email = email;
        this.passWord = passWord;
        this.confirmPassword = confirmPassword;
        this.address = address;
        this.position = position;
    }

    public Account() {
    }
}
