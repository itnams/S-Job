package com.example.s_job.Model;

public class Company {

    String nameCompany;
    String sdt;
    String diaChi;
    String Email;
    String pass;



    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
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

    public Company (String nameCompany, String sdt, String diaChi, String email, String pass) {

        this.nameCompany = nameCompany;
        this.sdt = sdt;
        this.diaChi = diaChi;
        Email = email;
        this.pass = pass;
    }

    public Company() {
    }
}
