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
    Account account;
    EditText name, email, pass, confirmPass, phone, address;
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
                if (rdoCompany.isChecked()) {
                    chucvu = "Company";
                } else {
                    chucvu = "User";
                }
                if (confirmPass.getText().toString().equals(pass.getText().toString())) {
                    String douutien = "Cao";
                    String trangthai = "Bình thường";
                    String emailPattern = "[a-zA-Z0-9._-]+@gmail.com";
                    if (chucvu.equals("Company")) {
                        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()
                                || confirmPass.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                            Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                        } else {
                            mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot key : snapshot.getChildren()) {
                                            Account account1 = key.getValue(Account.class);
                                            String emailkt = account1.email;
                                            String userkt = account1.nameUser;
                                            if (email.getText().toString().trim().matches(emailPattern)) {
                                                if (emailkt.equals(email.getText().toString()) || userkt.equals(name.getText().toString())) {
                                                    Toast.makeText(SignUp.this, "Tài khoản đã có người đăng ký", Toast.LENGTH_SHORT).show();
                                                    break;
                                                } else {
                                                    account = new Account(name.getText().toString(), email.getText().toString(), pass.getText().toString(), phone.getText().toString(), address.getText().toString(), chucvu, douutien, trangthai);
                                                    mData.child("Pending").child(email.getText().toString().replace("@gmail.com", "")).setValue(account);
                                                    Toast.makeText(SignUp.this, "Vui lòng chờ xét duyệt !", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            } else {
                                                Toast.makeText(SignUp.this, "Địa chỉ email không hợp lệ ([a-zA-Z0-9._-]+@gmail.com)", Toast.LENGTH_SHORT).show();
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
                        if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()
                                || confirmPass.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                            Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                        } else {
                            mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        for (DataSnapshot key : snapshot.getChildren()) {
                                            Account account1 = key.getValue(Account.class);
                                            String emailkt = account1.email;
                                            String userkt = account1.nameUser;
                                            if (email.getText().toString().trim().matches(emailPattern)) {
                                                if (emailkt.equals(email.getText().toString()) || userkt.equals(name.getText().toString())) {
                                                    Toast.makeText(SignUp.this, "Tài khoản đã có người đăng ký", Toast.LENGTH_SHORT).show();
                                                    break;
                                                } else {
                                                    account = new Account(name.getText().toString(), email.getText().toString(), pass.getText().toString(), phone.getText().toString(), address.getText().toString(), chucvu, douutien, trangthai);
                                                    mData.child("User").child(email.getText().toString().replace("@gmail.com", "")).setValue(account);
                                                    Toast.makeText(SignUp.this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }
                                            } else {
                                                Toast.makeText(SignUp.this, "Địa chỉ email không hợp lệ ([a-zA-Z0-9._-]+@gmail.com)", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
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