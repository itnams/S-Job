package com.example.s_job.Model;

import java.util.HashMap;
import java.util.Map;

public class Company {
    String image;
    String fullName;
    String nameCompany;
    String sdt;
    String diaChi;
    String Email;
    String pass;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public Company(String image, String fullName, String nameCompany, String sdt, String diaChi, String email, String pass) {
        this.image = image;
        this.fullName = fullName;
        this.nameCompany = nameCompany;
        this.sdt = sdt;
        this.diaChi = diaChi;
        Email = email;
        this.pass = pass;
    }

    public Company() {
        this.image = "";
        this.nameCompany = "";
        this.sdt = "";
        this.diaChi = "";
        this.Email = "";
        this.pass = "";
        this.fullName = "";
    }

    public Map toMapFormUser() {
        HashMap resuflt = new HashMap();
        resuflt.put("address", this.diaChi);
        resuflt.put("email", this.Email+"@gmail.com");
        resuflt.put("phone", this.sdt);
        resuflt.put("passWord", this.pass);

        return resuflt;
    }

    public Map toMapFormCompany() {
        HashMap resuflt = new HashMap();

        resuflt.put("fullName", this.fullName);
        resuflt.put("Image", this.image);


        return resuflt;
    }
}
