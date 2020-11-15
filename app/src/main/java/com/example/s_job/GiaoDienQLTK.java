package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GiaoDienQLTK extends AppCompatActivity {
    ListView listviewtk;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_q_l_t_k);
        listviewtk = findViewById(R.id.lstviewtk);
        arrayList = new ArrayList<>();
        arrayList.add("Tên Tài Khoản    Loại Tài Khoản  Ưu Tiên   Trạng Thái");
        arrayList.add("DinhLong01       Người dùng        Cao          Bình Thường");
        arrayList.add("VietNam02        Người dùng        Thấp         Khóa");
        arrayList.add("QuangTuan03    Người dùng       Thấp         Bình Thường");
        arrayList.add("DaiGiaPhat       Doanh Nghiệp      Cao          Bình Thường");
        arrayList.add("NhanDoanh        Doanh Nghiệp    Cao          Bình Thường");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,arrayList);
        listviewtk.setAdapter(arrayAdapter);
        listviewtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GiaoDienQLTK.this, ChiTietTaiKhoan.class);
                //.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomout));
                Toast.makeText(GiaoDienQLTK.this,arrayList.get(position),Toast.LENGTH_SHORT).show();
                startActivity(intent);
             //   startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getApplicationContext()).toBundle());
            }
        });
    }
}