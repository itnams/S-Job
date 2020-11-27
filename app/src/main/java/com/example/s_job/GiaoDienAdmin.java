package com.example.s_job;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.activity.Login;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GiaoDienAdmin extends AppCompatActivity {
    TextView tvqltk;
    TextView tvcnyc;
    TextView tvweb;
    Login login;
    DatabaseReference mData;
    TextView tenAdmin, emailAdmin, sdtAdmin, diaChiAmdin;
    TextView tvlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_admin);
        tvqltk = findViewById(R.id.tvqltk);
        tvlogout = findViewById(R.id.tvlogout);
        tvcnyc = findViewById(R.id.tvcnyc);
        tvweb = findViewById(R.id.tvweb);
        tenAdmin = findViewById(R.id.tenAdmin);
        emailAdmin = findViewById(R.id.emailAdmin);
        sdtAdmin = findViewById(R.id.sdtAdmin);
        diaChiAmdin = findViewById(R.id.diaChiAdmin);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Account account = dataSnapshot.getValue(Account.class);
                String tenAdmin1 = account.nameUser;
                String emailAdmin1 = account.email;
                String sdtAdmin1 = account.phone;
                String diaChiAdmin1 = account.address;
//                if(login.trangThai == "Khóa")
//                {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienAdmin.this);
//                    builder1.setTitle("Thông báo !");
//                    builder1.setMessage("Tài khoản của bạn đang bị khoá vui lòng liên hệ Admin để biết thêm chi tiết ! Click Đồng ý để quay lại yrang đăng nhập ! ");
//                    builder1.setCancelable(true);
//                    builder1.setPositiveButton("Đồng ý",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    Intent intent = new Intent(GiaoDienAdmin.this,Login.class);
//                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                                }
//                            });
//                    AlertDialog alert11 = builder1.create();
//                    alert11.show();
//                }
                if(login.tentaikhoanAdmin.equals(tenAdmin1)){
                    tenAdmin.setText(tenAdmin1);
                    emailAdmin.setText(emailAdmin1);
                    sdtAdmin.setText(sdtAdmin1);
                    diaChiAmdin.setText(diaChiAdmin1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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