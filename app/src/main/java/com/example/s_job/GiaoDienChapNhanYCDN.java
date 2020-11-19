package com.example.s_job;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Custom.Custom_lv_yeucau;

import java.util.ArrayList;

public class GiaoDienChapNhanYCDN extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chap_nhan_y_c_d_n);
        lv = findViewById(R.id.lv_yeucau);
        ArrayList<Object> oj = new ArrayList<>();
      //dbFire fb = new dbfire();
      //oj = fb.dsdn();
        oj.add(new Object());
        oj.add(new Object());
        lv.setAdapter(new Custom_lv_yeucau(getApplicationContext(), oj));
    }
}