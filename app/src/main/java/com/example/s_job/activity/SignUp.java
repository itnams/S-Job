package com.example.s_job.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
Account account;
EditText name,email,pass,confirmPass,phone,address;
RadioGroup rdoGroup;
RadioButton rdoCompany,rdoUser;
Button btnSignUp;
DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mData = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirmPassword);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        rdoGroup = findViewById(R.id.rdoGroup);
        rdoCompany = findViewById(R.id.rdoCompany);
        rdoUser = findViewById(R.id.rdoUser);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chucvu;
                if(rdoCompany.isChecked())
                {
                    chucvu ="Company";
                }
                else
                {
                    chucvu = "User";
                }
                if(confirmPass.getText().toString().equals(pass.getText().toString()))
                {
                    String douutien = "Cao";
                    String trangthai = "Bình thường";
                    account = new Account(name.getText().toString(),email.getText().toString(),pass.getText().toString(),phone.getText().toString(),address.getText().toString(),chucvu.toString(),douutien,trangthai);
                    if(chucvu.equals("Company"))
                    {
                        if(account.email.equals("")||account.address.equals("")||account.douutien.equals("")||account.nameUser.equals("")||account.passWord.equals("")||account.phone.equals("")||account.position.equals(""))
                        {
                            Toast.makeText(SignUp.this,"Vui lòng nhập đầy đủ thông tin !",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            mData.child("User").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    Account account = snapshot.getValue(Account.class);
                                    String emailkt = account.email;
                                    String userkt = account.nameUser;
                                    if(account.email.equals(emailkt) || account.nameUser.equals(userkt))
                                    {
                                        Toast.makeText(SignUp.this,"Tài khoản đã có người đăng ký",Toast.LENGTH_SHORT).show();
                                    }else {
                                        mData.child("Pending").child(email.getText().toString().replace("@gmail.com","")).setValue(account);
                                        Toast.makeText(SignUp.this,"Vui lòng chờ xét duyệt !",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
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

                        }
                    }
                    else
                    {
                        if(account.email.equals("")||account.address.equals("")||account.douutien.equals("")||account.nameUser.equals("")||account.passWord.equals("")||account.phone.equals("")||account.position.equals(""))
                        {
                            Toast.makeText(SignUp.this,"Vui lòng nhập đầy đủ thông tin !",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            mData.child("User").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    Account account = snapshot.getValue(Account.class);
                                    String emailkt = account.email;
                                    String userkt = account.nameUser;
                                    if(account.email.equals(emailkt) || account.nameUser.equals(userkt))
                                    {
                                        Toast.makeText(SignUp.this,"Tài khoản đã có người đăng ký",Toast.LENGTH_SHORT).show();
                                    }else {
                                        mData.child("User").child(email.getText().toString().replace("@gmail.com","")).setValue(account);
                                        Toast.makeText(SignUp.this,"Đăng ký thành công !",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
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
                        }
                    }
                }
                else
                {
                    Toast.makeText(SignUp.this,"Đăng ký thất bại !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}