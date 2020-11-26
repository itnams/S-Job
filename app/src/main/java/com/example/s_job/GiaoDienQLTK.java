package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.activity.SignUp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GiaoDienQLTK extends AppCompatActivity {
    ListView listviewtk;
    ArrayList<String> dstaikhoantk;
    DatabaseReference mData;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_q_l_t_k);
        listviewtk = findViewById(R.id.lstviewtk);
        dstaikhoantk = new ArrayList<String>();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Account account = dataSnapshot.getValue(Account.class);
                String name = account.nameUser;
                String position = account.position;
                String douutien = account.douutien;
                String trangthai = account.trangthai;

                    dstaikhoantk.add("Tên TK: " + name + "-" + "Loại TK: " + position + "-" + "Độ ưu tiên: " + douutien + "-" + "Trạng thái: " + trangthai);

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
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,dstaikhoantk);
        listviewtk.setAdapter(arrayAdapter);
        listviewtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mData.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Intent intent = new Intent(GiaoDienQLTK.this,ChiTietTaiKhoan.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        String data=(String)parent.getItemAtPosition(position);
                        String itemText = (String) ((TextView) view).getText();
                        Toast.makeText(GiaoDienQLTK.this,itemText,Toast.LENGTH_SHORT).show();
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

            }
        });
    }
}