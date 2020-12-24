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
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    Account account1;
    EditText name, email1, pass, confirmPass, phone, address;
    RadioGroup rdoGroup;
    RadioButton rdoCompany, rdoUser;
    Button btnSignUp;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mData = FirebaseDatabase.getInstance().getReference();
        name = findViewById(R.id.name);
        email1 = findViewById(R.id.email);
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
                if (rdoCompany.isChecked()) {
                    chucvu = "Company";
                } else {
                    chucvu = "User";
                }
                if (confirmPass.getText().toString().equals(pass.getText().toString())) {
                    String douutien = "Cao";
                    String trangthai = "Bình thường";
                    if (chucvu.equals("Company")) {
                        if (email1.getText().toString().isEmpty() || name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()
                                || confirmPass.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                            Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();

                        } else {
                            mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists())
                                    {
                                        for(DataSnapshot key : snapshot.getChildren())
                                        {
                                            Account account = key.getValue(Account.class);
                                            String emailkt = account.email;
                                            String userkt = account.nameUser;
                                            String emailPattern = "[a-zA-Z0-9._-]+@gmail.com+";
                                            if (email1.getText().toString().matches(emailPattern)) {
                                                if (email1.getText().toString().equals(emailkt.replace("@gmail.com",""))) {
                                                    Toast.makeText(SignUp.this, "Tài khoản đã có người đăng ký", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    account1 = new Account(name.getText().toString(), email1.getText().toString(), pass.getText().toString(), phone.getText().toString(), address.getText().toString(), chucvu.toString(), douutien, trangthai);
                                                    mData.child("Pending").child(email1.getText().toString().replace("@gmail.com", "")).setValue(account1);
                                                    Toast.makeText(SignUp.this, "Vui lòng chờ xét duyệt !", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            } else {
                                                Toast.makeText(SignUp.this, "Email không hợp lệ !", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                    } else {
                        if (email1.getText().toString().isEmpty() || name.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || confirmPass.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                            Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();

                        } else {
                            mData.child("User").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    Account account = snapshot.getValue(Account.class);
                                    String emailkt = account.email;
                                    String userkt = account.nameUser;
                                    String emailPattern = "[a-zA-Z0-9._-]+@gmail.com+";
                                    if (email1.getText().toString().matches(emailPattern)) {
                                        if (email1.getText().toString().equals(emailkt) || name.getText().toString().equals(userkt))
                                        {
                                            Toast.makeText(SignUp.this, "Tài khoản đã có người đăng ký", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            account1 = new Account(name.getText().toString(), email1.getText().toString(), pass.getText().toString(), phone.getText().toString(), address.getText().toString(), chucvu.toString(), douutien, trangthai);
                                            mData.child("User").child(email1.getText().toString().replace("@gmail.com", "")).setValue(account1);
                                            Toast.makeText(SignUp.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(SignUp.this, "Email không hợp lệ !", Toast.LENGTH_SHORT).show();
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
                } else {
                    Toast.makeText(SignUp.this, "Mật khẩu không khớp !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}