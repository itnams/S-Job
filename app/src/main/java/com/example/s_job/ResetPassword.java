package com.example.s_job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.s_job.Datacode.Account;
import com.example.s_job.Model.Company;
import com.example.s_job.db_firebase.dbFireBase;
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
    DatabaseReference mData;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        listResetPass = findViewById(R.id.listresetpass);
        passArrayList = new ArrayList<ResetPass>();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("ListResetPass").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ResetPass resetPass = snapshot.getValue(ResetPass.class);
                passArrayList.add(new ResetPass(resetPass.emailrs,resetPass.userreset));
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

        listResetPass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String emailluu = passArrayList.get(position).emailrs;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ResetPassword.this);
                builder1.setTitle("Vui lòng lựa chọn");
                builder1.setMessage("Click Đồng ý để xóa, Click Từ chối để quay về");
                builder1.setPositiveButton("Từ Chối",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder1.setNegativeButton("Đồng Ý",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               mData.child("ListResetPass").child(emailluu.replace("@gmail.com","")).removeValue();
                                passArrayList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }
}