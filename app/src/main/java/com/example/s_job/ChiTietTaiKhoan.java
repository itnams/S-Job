package com.example.s_job;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ChiTietTaiKhoan extends AppCompatActivity {
    GiaoDienQLTK giaoDienQLTK;
    EditText edttentaikhoan, edtmatkhau, edtdouutien, edttrangthai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        edttentaikhoan = findViewById(R.id.edttentaikhoan);
        edtmatkhau = findViewById(R.id.edtmatkhau);
        edtdouutien = findViewById(R.id.edtdouutien);
        edttrangthai = findViewById(R.id.edttrangthai);

        edttentaikhoan.setText(giaoDienQLTK.nameTK);
        edtmatkhau.setText(giaoDienQLTK.passwordtk);
        edtdouutien.setText(giaoDienQLTK.douutien);
        edttrangthai.setText(giaoDienQLTK.trangthai);
    }
}