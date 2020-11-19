package com.example.s_job.db_firebase;

import com.example.s_job.Model.Company;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dbFireBase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Company");

    public dbFireBase() {


    }

    public void add_Company(Company company) {

        ArrayList<Company> lst = new ArrayList<>();
        lst.add(new Company(0, "a", 2222, "diachi", "email", "pass"));
        lst.add(new Company(0, "a1", 2222, "diachi", "email", "pass"));
        lst.add(new Company(0, "a2", 2222, "diachi", "email", "pass"));
        lst.add(new Company(0, "a3", 2222, "diachi", "email", "pass"));
        Map key = new HashMap();
        key.put("ListUser", lst);
        myRef.child("ListCompany").setValue(key);
    }

}
