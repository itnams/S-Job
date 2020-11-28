package com.example.s_job;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.Model.Company;
import com.example.s_job.db_firebase.dbFireBase;
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
                String sdt = account.phone;
                dstaikhoanyc.add(name + " " + sdt);
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
        listviewtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienChapNhanYCDN.this);
                builder1.setTitle("Vui lòng lựa chọn !");
                builder1.setMessage("Click Đồng ý để chấp nhận yêu cầu, Click Từ chối để xóa yêu cầu");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Từ chối",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Đồng ý",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mData.child("Pending").addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        Account account = dataSnapshot.getValue(Account.class);
                                        String address = account.address;
                                        String email = account.email;
                                        String nameUser = account.nameUser;
                                        String passWord = account.passWord;
                                        String phone = account.phone;
                                        String position = account.position;
                                        String douutien = account.douutien;
                                        String trangthai = account.trangthai;
                                        if(dstaikhoanyc.get(Integer.parseInt(String.valueOf(position1))).equals((nameUser + " " + phone).toString())) {
                                            Account account1 = new Account(nameUser, email, passWord, phone, address, position, douutien, trangthai);
                                            //Nhan ------------
                                            Company ad = new Company();
                                            ad.setEmail(email.replace("@gmail.com", ""));
                                            new dbFireBase().addDataToCompany(ad);
                                            // ------------
                                            mData.child("User").child(email.toString().replace("@gmail.com", "")).setValue(account1);
                                            mData.child("Pending").child(email.toString().replace("@gmail.com", "")).removeValue();
                                        };
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
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
}