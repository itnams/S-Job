package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GiaoDienTrangTuyenDung extends AppCompatActivity {
    ListView listViewweb;
    ArrayList<String> arrayList;
    Button btnthemweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_trang_tuyen_dung);
        listViewweb = findViewById(R.id.lstweb);
        btnthemweb = findViewById(R.id.btnthemweb);
        btnthemweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienTrangTuyenDung.this, ThemWebTuyenDung.class);
                startActivity(intent);
            }
        });
        arrayList = new ArrayList<>();
        arrayList.add("VietNamWork.com");
        arrayList.add("TimViecNhanh24h.vn");
        arrayList.add("CongViecHome.com");
        arrayList.add("TimViecNhanh24h.vn");
        arrayList.add("TimViecNhanh24h.vn");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        listViewweb.setAdapter(arrayAdapter);
        listViewweb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GiaoDienTrangTuyenDung.this, arrayList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}