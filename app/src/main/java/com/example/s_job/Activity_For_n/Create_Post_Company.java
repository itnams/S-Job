package com.example.s_job.Activity_For_n;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;

public class Create_Post_Company extends AppCompatActivity {
    EditText tieuDe, deLine, mucLuong, bangCap, nganhNghe, soLuongTuyen, diaChi, moTa;
    ImageButton date, vitri;
    Button troVe, Luu;
    PostForCompany postForCompany = new PostForCompany();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post__company);
        setControl();
        setEvent();
    }

    private void setEvent() {

        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToDataBase();
            }
        });
    }

    private void SendToDataBase() {
        postForCompany.setCompany(Company_Profile.company);
        postForCompany.setTieuDe(tieuDe.getText().toString());
        postForCompany.setDeline(deLine.getText().toString());
        postForCompany.setMucLuong(mucLuong.getText().toString());
        postForCompany.setBangCap(bangCap.getText().toString());
        postForCompany.setNganhNghe(nganhNghe.getText().toString());
        postForCompany.setSoLuong(soLuongTuyen.getText().toString());
        postForCompany.setDiaChi(diaChi.getText().toString());
        postForCompany.setMota(moTa.getText().toString());
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
        //button
        date = findViewById(R.id.imgBtn_date);
        vitri = findViewById(R.id.imgBtn_vitri);
        troVe = findViewById(R.id.btn_trove);
        Luu = findViewById(R.id.btn_luu);
    }
}