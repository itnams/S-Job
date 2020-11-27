package com.example.s_job.Activity_For_n;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.R;

public class Create_Post_Company extends AppCompatActivity {
    EditText tieuDe, deLine, mucLuong, bangCap, nganhNghe, soLuongTuyen, diaChi, moTa;
    ImageButton date, vitri;
    Button troVe, Luu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post__company);
        setControl();
        setEvent();
    }

    private void setEvent() {
    }

    private void setControl() {
        tieuDe = findViewById(R.id.et_tieuDe);
        deLine = findViewById(R.id.et_deLine);
        mucLuong = findViewById(R.id.et_mucLuong);
        bangCap = findViewById(R.id.et_bangCap);
        nganhNghe = findViewById(R.id.et_nganhNghe);
        soLuongTuyen = findViewById(R.id.et_soLuongTuyen);
        diaChi = findViewById(R.id.et_diaChi);
        moTa = findViewById(R.id.et_moTa);
    }
}