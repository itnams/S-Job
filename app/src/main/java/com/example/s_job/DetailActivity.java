package com.example.s_job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.activity.Login;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
TextView txtTieuDePost,txtHanNop,txtTinhThanhDetail,txtMucLuong,txtSoLuongTuyen,
        txtBangCap,txtNganhNghe,txtDiaChi,txtDetail;
Button btnNhanXet;
Button btnLuuTin;
String key;
RatingBar ratingPos;
EditText edtComment;
ListView listviewComment;
Login login;
DatabaseReference mData;
ArrayList<Comment>commentArrayList;
User_Home user_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnLuuTin = findViewById(R.id.btnLuuTin);
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
        ratingPos = findViewById(R.id.ratingPos);
        edtComment = findViewById(R.id.edtComment);
        listviewComment = findViewById(R.id.listviewComment);
        txtTieuDePost.setText(user_home.tieude);
        txtHanNop.setText("Hạn nộp:"+user_home.ngayDang);
        txtTinhThanhDetail.setText(user_home.tinhThanh);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("All-Post").child(key).child("Luu-tin").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Luutin luutin = snapshot.getValue(Luutin.class);
                if (luutin.username.equals(login.userLogin))
                {
                    btnLuuTin.setText("Bỏ lưu tin");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnLuuTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLuuTin.getText().toString().equals("Lưu tin"))
                {
                    mData.child("All-Post").child(key).child("Luu-tin").child(login.userLogin).child("username").setValue(login.userLogin);
                    btnLuuTin.setText("Bỏ lưu tin");
                }
               else
                {
                    mData.child("All-Post").child(key).child("Luu-tin").child(login.userLogin).removeValue();
                    btnLuuTin.setText("Lưu tin");
                }
            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("MucLuong").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtMucLuong.setText("Mức lương:"+snapshot.getValue().toString() + "VNĐ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("soLuongTuyen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtSoLuongTuyen.setText("Số lượng:"+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("bangCap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtBangCap.setText("Bằng cấp:"+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("nganhNghe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtNganhNghe.setText("Ngành nghề:"+snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("diaChi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txtDiaChi.setText("Địa chỉ:"+snapshot.getValue().toString());
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
                Comment comment = new Comment(login.userLogin,edtComment.getText().toString(),String.valueOf(ratingPos.getRating()).replace(".0",""));
                mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("Comment").push().setValue(comment);
                edtComment.setText("");
            }
        });
        commentArrayList = new ArrayList<>();
        mData.child("Company").child("Post-Company").child(user_home.emai).child(key).child("Comment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Comment comment = snapshot.getValue(Comment.class);
                commentArrayList.add(new Comment(comment.user,comment.comment,comment.ratting));
                commentAdapter commentAdapter = new commentAdapter(DetailActivity.this,R.layout.dong_comment,commentArrayList);
                listviewComment.setAdapter(commentAdapter);
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listviewComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }
}