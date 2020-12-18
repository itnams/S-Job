package com.example.s_job.Model;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class PostForCompany {
    Company company;
    String tieuDe;
    String deLine;
    String nganhNghe;
    String bangCap;
    String MucLuong;
    String diaChi;
    String tinhThanh;
    String soLuongTuyen;
    String Mota;
    String Key;
    public Map toMapCompany() {
        Map data = new HashMap();
        data.put("nameUser", this.company.nameCompany);
        data.put("tieuDe", this.tieuDe);
        data.put("deLine", this.deLine);
        data.put("nganhNghe", this.nganhNghe);
        data.put("bangCap", this.bangCap);
        data.put("MucLuong", this.MucLuong);
        data.put("diaChi", this.diaChi);
        data.put("tinhThanh", this.tinhThanh);
        data.put("soLuongTuyen", this.soLuongTuyen);
        data.put("Mota", this.Mota);
        return data;
    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Map ToMap_AllPost() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        Map data = new HashMap();
        data.put("emai", this.company.getEmail());
        data.put("tieuDe", this.tieuDe);
        data.put("Mota", this.Mota);
        data.put("tinhThanh", this.tinhThanh);
        data.put("key", this.Key);
        data.put("ngayDang", "" + currentDay + "/" + currentMonth + "/" + currentYear);

        return data;
    }

    public PostForCompany() {
        this.company = new Company();
        this.tieuDe = "";
        this.deLine = "";
        this.nganhNghe = "";
        this.bangCap = "";
        this.MucLuong = "";
        this.diaChi = "";
        this.tinhThanh = "";
        this.soLuongTuyen = "";
        this.Mota = "";
        this.Key = "";
    }


    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public PostForCompany(Company company, String tieuDe, String deline, String nganhNghe, String bangCap, String mucLuong, String diaChi, String soLuongTuyen, String mota) {
        this.company = company;
        this.tieuDe = tieuDe;
        this.deLine = deline;
        this.nganhNghe = nganhNghe;
        this.bangCap = bangCap;
        this.MucLuong = mucLuong;
        this.diaChi = diaChi;
        this.soLuongTuyen = soLuongTuyen;
        this.Mota = mota;
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
        return deLine;
    }

    public void setDeline(String deline) {
        this.deLine = deline;
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

    public String getSoLuongTuyen() {
        return soLuongTuyen;
    }

    public void setSoLuongTuyen(String soLuongTuyen) {
        this.soLuongTuyen = soLuongTuyen;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }
}
