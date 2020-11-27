package com.example.s_job.Model;

public class PostForCompany {
    Company company;
    String tieuDe;
    String deline;
    String nganhNghe;
    String bangCap;
    String MucLuong;
    String diaChi;
    String SoLuong;
    String Mota;

    public PostForCompany() {
        this.company = new Company();
        this.tieuDe = "";
        this.deline = "";
        this.nganhNghe = "";
        this.bangCap = "";
        this.MucLuong = "";
        this.diaChi = "";
        this.SoLuong = "";
        this.Mota = "";
    }

    public PostForCompany(Company company, String tieuDe, String deline, String nganhNghe, String bangCap, String mucLuong, String diaChi, String soLuong, String mota) {
        this.company = company;
        this.tieuDe = tieuDe;
        this.deline = deline;
        this.nganhNghe = nganhNghe;
        this.bangCap = bangCap;
        MucLuong = mucLuong;
        this.diaChi = diaChi;
        SoLuong = soLuong;
        Mota = mota;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getDeline() {
        return deline;
    }

    public void setDeline(String deline) {
        this.deline = deline;
    }

    public String getNganhNghe() {
        return nganhNghe;
    }

    public void setNganhNghe(String nganhNghe) {
        this.nganhNghe = nganhNghe;
    }

    public String getBangCap() {
        return bangCap;
    }

    public void setBangCap(String bangCap) {
        this.bangCap = bangCap;
    }

    public String getMucLuong() {
        return MucLuong;
    }

    public void setMucLuong(String mucLuong) {
        MucLuong = mucLuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
