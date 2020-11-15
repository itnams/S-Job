package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GiaoDienAdmin extends AppCompatActivity {
    TextView tvqltk;
    TextView tvcnyc;
    TextView tvweb;
    TextView tvlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_admin);
        tvqltk = findViewById(R.id.tvqltk);
        tvlogout = findViewById(R.id.tvlogout);
        tvcnyc = findViewById(R.id.tvcnyc);
        tvweb = findViewById(R.id.tvweb);
        tvqltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this,GiaoDienQLTK.class);
                startActivity(intent);
            }
        });
        tvcnyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this,GiaoDienChapNhanYCDN.class);
                startActivity(intent);
            }
        });
        tvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this,GiaoDienTrangTuyenDung.class);
                startActivity(intent);
            }
        });
    }
}