package com.example.s_job.db_firebase;

import com.example.s_job.Model.Company;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dbFireBase {
    public FirebaseDatabase database;
    public DatabaseReference myRef;


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

}
