package com.example.s_job.db_firebase;

import androidx.annotation.NonNull;

import com.example.s_job.Model.Company;
import com.example.s_job.Model.PostForCompany;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class dbFireBase {
    public FirebaseDatabase database;
    public DatabaseReference myRef;


    public dbFireBase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Company");

    }

    public void removePost(PostForCompany postForCompany) {
        myRef.child("Post-Company")
                .child(postForCompany.getCompany().getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot key : snapshot.getChildren()) {
                                HashMap map = key.getValue(HashMap.class);
                                if (postForCompany.getTieuDe().equals(map.get("tieuDe").toString())
                                        && postForCompany.getDeline().equals(map.get("deLine").toString())) {
                                    myRef.child("Post-Company")
                                            .child(postForCompany.getCompany().getEmail()).child(key.getKey()).removeValue();
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void upDateCompanyFormUser(Company company) {
        myRef = database.getReference("User");
        myRef.child(company.getEmail()).updateChildren(company.toMapFormUser());
    }

    public void addDataToCompany(Company User) {

        myRef.child("Additional-Company").child(User.getEmail()).setValue(User.toMapFormCompany());
    }

    public void upDateCompanyFormCompany(Company company) {

        myRef.child("Additional-Company").child(company.getEmail()).updateChildren(company.toMapFormCompany());
    }

    public void NewPoserForCompany(PostForCompany postForCompany) {
        myRef.child("Post-Company")
                .child(postForCompany.getCompany().getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    myRef.child("Post-Company")
                            .child(postForCompany.getCompany().getEmail())
                            .child((snapshot.getChildrenCount()) + "").setValue(postForCompany.toMapCompany());
                    sendToAll_post(postForCompany);
                } else {

                    myRef.child("Post-Company")
                            .child(postForCompany.getCompany().getEmail())
                            .child("" + snapshot.getChildrenCount()).setValue(postForCompany.toMapCompany());
                    sendToAll_post(postForCompany);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void sendToAll_post(PostForCompany postForCompany) {

        myRef = database.getReference("All-Post");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myRef.child(snapshot.getChildrenCount() + "").setValue(postForCompany.ToMap_AllPost());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
