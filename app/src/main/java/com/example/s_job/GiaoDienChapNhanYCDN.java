package com.example.s_job;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class GiaoDienChapNhanYCDN extends AppCompatActivity {
    ListView listviewtk;
    ArrayList<String> dstaikhoanyc;
    DatabaseReference mData;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chap_nhan_y_c_d_n);
        listviewtk = findViewById(R.id.lv_yeucau);
        dstaikhoanyc = new ArrayList<String>();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("Pending").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Account account = dataSnapshot.getValue(Account.class);
                String name = account.nameUser;
                String sdt = account.passWord;
                dstaikhoanyc.add(name + sdt);
                arrayAdapter.notifyDataSetChanged();
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
         arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,dstaikhoanyc);
        listviewtk.setAdapter(arrayAdapter);
    }
}