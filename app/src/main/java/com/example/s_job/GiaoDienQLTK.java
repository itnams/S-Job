package com.example.s_job;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.DataBase.dbFireBase;
import com.example.s_job.Datacode.Account;
import com.example.s_job.activity.Login;
import com.example.s_job.activity.SignUp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiaoDienQLTK extends AppCompatActivity {
    ListView listviewtk;
    ArrayList<String> dstaikhoantk;
    DatabaseReference mData;
    public static String nameTK1, passwordtk1, douutien1, trangthai1, emailkeyword;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_q_l_t_k);
        listviewtk = findViewById(R.id.lstviewtk);
        EditText edttim = findViewById(R.id.edttim);
        dstaikhoantk = new ArrayList<String>();
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Account account = dataSnapshot.getValue(Account.class);
                String email = account.email;
                    dstaikhoantk.add(email);
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
        edttim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                arrayAdapter.clear();
                mData.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Account account = dataSnapshot.getValue(Account.class);
                        String email = account.email;
                        if (email.contains(edttim.getText().toString())) {
                                dstaikhoantk.add(email);
                                arrayAdapter = new ArrayAdapter(GiaoDienQLTK.this, R.layout.support_simple_spinner_dropdown_item, dstaikhoantk);
                                listviewtk.setAdapter(arrayAdapter);
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

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dstaikhoantk);
        listviewtk.setAdapter(arrayAdapter);
        listviewtk.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String emailkey = dstaikhoantk.get(position).replace("@gmail.com", "");

                AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienQLTK.this);
                builder1.setTitle("Vui lòng lựa chọn !");
                builder1.setMessage("Bạn muốn xóa tài khoản " + emailkey);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Quay về",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Đồng ý",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mData.child("User").child(emailkey).removeValue();
                                dstaikhoantk.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(GiaoDienQLTK.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            }
        });
        listviewtk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String emailkey = dstaikhoantk.get(position).replace("@gmail.com", "");
                mData.child("User").child(emailkey).child("nameUser").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        nameTK1 = snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mData.child("User").child(emailkey).child("passWord").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        passwordtk1 = snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mData.child("User").child(emailkey).child("douutien").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        douutien1 = snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                mData.child("User").child(emailkey).child("trangthai").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        trangthai1 = snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                emailkeyword = emailkey;
                Intent intent = new Intent(getApplicationContext(), ChiTietTaiKhoan.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}