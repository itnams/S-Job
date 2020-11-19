package com.example.s_job.Model;

public class Company {
    int id;
    String nameCompany;
    int sdt;
    String diaChi;
    String Email;
    String pass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Company(int id, String nameCompany, int sdt, String diaChi, String email, String pass) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.sdt = sdt;
        this.diaChi = diaChi;
        Email = email;
        this.pass = pass;
    }

    public Company() {
    }
}
