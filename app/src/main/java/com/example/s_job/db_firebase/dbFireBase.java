package com.example.s_job.db_firebase;

import androidx.annotation.NonNull;

import com.example.s_job.Model.Company;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class dbFireBase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Company");

    public dbFireBase() {


    }



    public Company getData_InUser(String user) {
        DatabaseReference myRef = database.getReference("User");
        Company data = null;
        myRef.child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Map key = dataSnapshot.getValue(Map.class);

                    data.setPass((String) key.get("passWord"));
                    data.setNameCompany((String) key.get("nameUser"));
                    data.setSdt((String) key.get("phone"));
                    data.setEmail((String) key.get("email"));
                    data.setDiaChi((String) key.get("address"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if (data != null) {
            return data;
        } else {
            return null;
        }
    }
}
