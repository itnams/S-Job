package com.example.s_job.db_firebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.s_job.Model.Company;
import com.example.s_job.Model.PostForCompany;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class dbFireBase {
    public FirebaseDatabase database;
    public DatabaseReference myRef;
    public FirebaseStorage storage;
    public StorageReference mStorageRef;

    public dbFireBase() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Company");
        //fire store

        storage = FirebaseStorage.getInstance();
        mStorageRef = storage.getReference();

    }

    public void remove_in_all_post(PostForCompany postForCompany) {
        myRef = database.getReference("All-Post");
        myRef.child(postForCompany.getKey()).removeValue();
    }

    public void removePost(PostForCompany postForCompany, Activity activity) {
        myRef.child("Post-Company").child(postForCompany.getCompany().getEmail()).child(postForCompany.getKey()).removeValue();
        remove_in_all_post(postForCompany);
        Toast.makeText(activity, "Remove Success!!! ", Toast.LENGTH_LONG).show();
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
                .child(postForCompany.getCompany().getEmail())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String Key = myRef.push().getKey();
                        postForCompany.setKey(Key);

                        myRef.child("Post-Company")
                                .child(postForCompany.getCompany().getEmail())
                                .child(Key).setValue(postForCompany.toMapCompany());
                        sendToAll_post(postForCompany);


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
                myRef.child(postForCompany.getKey()).setValue(postForCompany.ToMap_AllPost());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void UploadImgToFireBase(Uri img, String nameImg, Activity activity) {
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle("Upload...");
        dialog.show();
        mStorageRef.child(nameImg + ".jpg").putFile(img).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                dialog.dismiss();
                Toast.makeText(activity, "Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(activity, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0 * snapshot.getBytesTransferred() / snapshot
                        .getTotalByteCount());
                dialog.setMessage("Uploaded " + (int) progress + "%");
            }
        });

    }
}
