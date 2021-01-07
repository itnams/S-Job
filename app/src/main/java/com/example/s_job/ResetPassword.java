package com.example.s_job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResetPassword extends AppCompatActivity {
    ListView listResetPass;
    ArrayList<ResetPass> passArrayList;
    ResetPassAdapter adapter;
    ResetPass resetPass;
    DatabaseReference mData;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("ListResetPass").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ResetPass resetPass = snapshot.getValue(ResetPass.class);
                listResetPass = findViewById(R.id.listresetpass);
                passArrayList = new ArrayList<ResetPass>();
                passArrayList.add(new ResetPass(resetPass.emailrs,resetPass.newpass));
                adapter = new ResetPassAdapter(ResetPassword.this,R.layout.listview_resetpass,passArrayList);
                listResetPass.setAdapter(adapter);
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

    }
}