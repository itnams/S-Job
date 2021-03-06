package com.example.s_job;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiaoDienChapNhanYCDN extends AppCompatActivity {
//    ListView listviewtk;
//    ArrayList<String> dstaikhoanyc;
//    DatabaseReference mData;
//    ArrayAdapter arrayAdapter;
    ListView listyeucauDN;
    ArrayList<Account> ycdnList;
    ChapNhanYeuCauDoanhNghiepAdapter adapter;
    DatabaseReference mData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chap_nhan_y_c_d_n);
        listyeucauDN = findViewById(R.id.lv_yeucau);
        ycdnList = new ArrayList<Account>();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("Pending").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Account account = snapshot.getValue(Account.class);
                ycdnList.add(new Account(account.email,account.phone));
                adapter = new ChapNhanYeuCauDoanhNghiepAdapter(GiaoDienChapNhanYCDN.this,R.layout.listview_cnyc,ycdnList);
                listyeucauDN.setAdapter(adapter);
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

        listyeucauDN.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                String emailcanxoa = ycdnList.get(position1).email;
                String emailluu = ycdnList.get(position1).email;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienChapNhanYCDN.this);
                builder1.setTitle("Vui lòng lựa chọn");
                builder1.setMessage("Click Đồng ý để chấp nhập yêu cầu, Click Từ chối để xóa khỏi danh sách");
                builder1.setPositiveButton("Từ Chối",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mData.child("Pending").child(emailcanxoa.replace("@gmail.com", "")).removeValue();
                                ycdnList.remove(position1);
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder1.setNegativeButton("Đồng Ý",
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
                                        if(emailluu.equals(email))
                                        {
                                            Account account1 = new Account(nameUser, email, passWord, phone, address, position, douutien, trangthai);
                                            //Nhan ------------
                                            Company ad = new Company();
                                            ad.setEmail(email.replace("@gmail.com", ""));
                                            new dbFireBase().addDataToCompany(ad);
                                            // ------------
                                            mData.child("User").child(emailluu.replace("@gmail.com", "")).setValue(account1);
                                            mData.child("Pending").child(emailluu.replace("@gmail.com", "")).removeValue();
                                            ycdnList.remove(position1);
                                            adapter.notifyDataSetChanged();
                                        }
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
//        listviewtk = findViewById(R.id.lv_yeucau);
//        dstaikhoanyc = new ArrayList<String>();
//        mData = FirebaseDatabase.getInstance().getReference();
//        mData.child("Pending").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Account account = dataSnapshot.getValue(Account.class);
//                String name = account.nameUser;
//                String sdt = account.phone;
//                dstaikhoanyc.add(name + " " + sdt);
//                arrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dstaikhoanyc);
//        listviewtk.setAdapter(arrayAdapter);
//        listviewtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
//                String item = dstaikhoanyc.get(position1);
//                String[] namevssdt = item.split(" ");
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienChapNhanYCDN.this);
//                builder1.setTitle("Vui lòng lựa chọn !");
//                builder1.setMessage("Click Đồng ý để chấp nhận yêu cầu, Click Từ chối để xóa yêu cầu");
//                builder1.setPositiveButton("Từ chối",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                            mData.child("Pending").addChildEventListener(new ChildEventListener() {
//                                @Override
//                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                                    Account ac = snapshot.getValue(Account.class);
//                                    if(namevssdt[0].equals(ac.nameUser)&&namevssdt[1].equals(ac.getPhone())){
//                                        mData.child("Pending").child(ac.getEmail().replace("@gmail.com", "")).removeValue();
//                                        dstaikhoanyc.remove(position1);
//                                        arrayAdapter.notifyDataSetChanged();
//                                        return;
//                                    }
//                                }
//
//                                @Override
//                                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                                }
//
//                                @Override
//                                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                                }
//
//                                @Override
//                                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError error) {
//
//                                }
//                            });
//                            }
//                        });
//                builder1.setNegativeButton("Đồng ý",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                mData.child("Pending").addChildEventListener(new ChildEventListener() {
//                                    @Override
//                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                                        Account account = dataSnapshot.getValue(Account.class);
//                                        String address = account.address;
//                                        String email = account.email;
//                                        String nameUser = account.nameUser;
//                                        String passWord = account.passWord;
//                                        String phone = account.phone;
//                                        String position = account.position;
//                                        String douutien = account.douutien;
//                                        String trangthai = account.trangthai;
//                                        if (dstaikhoanyc.get(Integer.parseInt(String.valueOf(position1))).equals((nameUser + " " + phone).toString())) {
//                                            Account account1 = new Account(nameUser, email, passWord, phone, address, position, douutien, trangthai);
//                                            //Nhan ------------
//                                            Company ad = new Company();
//                                            ad.setEmail(email.replace("@gmail.com", ""));
//                                            new dbFireBase().addDataToCompany(ad);
//                                            // ------------
//                                            mData.child("User").child(email.toString().replace("@gmail.com", "")).setValue(account1);
//                                            mData.child("Pending").child(email.toString().replace("@gmail.com", "")).removeValue();
//                                            dstaikhoanyc.remove(position1);
//                                            arrayAdapter.notifyDataSetChanged();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                                    }
//
//                                    @Override
//                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                    }
//                                });
//                            }
//                        });
//                AlertDialog alert11 = builder1.create();
//                alert11.show();
//            }
//        });
    }
}