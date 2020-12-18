package com.example.s_job;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.activity.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
TextView txtTieuDePost,txtHanNop,txtTinhThanhDetail,txtMucLuong,txtSoLuongTuyen,
        txtBangCap,txtNganhNghe,txtDiaChi,txtDetail;
Button btnNhanXet;
String key;
EditText edtComment;
ListView listviewComment;
Login login;
DatabaseReference mData;
User_Home user_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txtTieuDePost = findViewById(R.id.txtTieuDePost);
        btnNhanXet = findViewById(R.id.btnNhanXet);
        txtHanNop = findViewById(R.id.txtHanNop);
        key = user_home.key.toString();
        txtTinhThanhDetail = findViewById(R.id.txtTinhThanhDetail);
        txtMucLuong = findViewById(R.id.txtMucLuong);
        txtSoLuongTuyen = findViewById(R.id.txtSoLuongTuyen);
        txtBangCap = findViewById(R.id.txtBangCap);
        txtNganhNghe = findViewById(R.id.txtNganhNghe);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtDetail = findViewById(R.id.txtDetail);
        edtComment = findViewById(R.id.edtComment);
        listviewComment = findViewById(R.id.listviewComment);
        txtTieuDePost.setText(user_home.tieude);
        txtHanNop.setText(user_home.ngayDang);
        txtTinhThanhDetail.setText(user_home.tinhThanh);
        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("MucLuong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtMucLuong.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("soLuongTuyen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtSoLuongTuyen.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("bangCap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtBangCap.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("nganhNghe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtNganhNghe.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("diaChi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtDiaChi.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("Mota").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtDetail.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnNhanXet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment = new Comment(login.userLogin,edtComment.getText().toString());
                mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("Comment").push().setValue(comment);
            }
        });
    }
}