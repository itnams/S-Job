package com.example.s_job.db_firebase;

import androidx.annotation.NonNull;

import com.example.s_job.Model.Company;
import com.example.s_job.Model.PostForCompany;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dbFireBase {
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    long count = 0;

    public dbFireBase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Company");

    }

    public void upDateCompanyFormUser(Company company) {
        myRef = database.getReference("User");
        myRef.child(company.getNameCompany()).updateChildren(company.toMapFormUser());
    }

    public void addDataToCompany(Company User) {

        myRef.child("Additional-Company").child(User.getNameCompany()).setValue(User.toMapFormCompany());
    }

    public void upDateCompanyFormCompany(Company company) {

        myRef.child("Additional-Company").child(company.getNameCompany()).updateChildren(company.toMapFormCompany());
    }

    public void NewPoserForCompany(PostForCompany postForCompany) {
        myRef.child("Post-Company")
                .child(postForCompany.getCompany().getNameCompany()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    myRef.child("Post-Company")
                            .child(postForCompany.getCompany().getNameCompany())
                            .child("" + snapshot.getChildrenCount() + 1).setValue(postForCompany.toMapCompany());
                }else {
                    myRef.child("Post-Company")
                            .child(postForCompany.getCompany().getNameCompany())
                            .child("" + snapshot.getChildrenCount() ).setValue(postForCompany.toMapCompany());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
