package com.example.s_job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.GiaoDienAdmin;
import com.example.s_job.MainActivity;
import com.example.s_job.MainActivity1;
import com.example.s_job.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
private TextView fogotPW;
private TextView Signup;
private Button btnLogin;
private EditText edtuser, edtpass;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference mData;
DatabaseReference myRef = database.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fogotPW = findViewById(R.id.resetpw);
        Signup = findViewById(R.id.sigup);
        btnLogin = findViewById(R.id.btnLogin);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        mData = FirebaseDatabase.getInstance().getReference();
        fogotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        Login.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bs_reset_password, (LinearLayout)findViewById(R.id.bs_change_password));
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(Login.this, SignUp.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        //Nếu nhánh user là mảng thì get dữ liệu sai
                        Account account = dataSnapshot.getValue(Account.class);
                        String nameUser = account.nameUser;
                        String passWord = account.passWord;
                        String position = account.position;
                        if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("Admin")){
                            Intent intent = new Intent(getApplicationContext(), GiaoDienAdmin.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            Toast.makeText(Login.this,"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();
                        }
                        else if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("User") )
                        {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                        else if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("Company")) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                            intent.putExtra("user", "company");
                            intent.putExtra("pass", "company");

                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            Toast.makeText(Login.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Login.this,"Mật khẩu tài khoản không đúng !",Toast.LENGTH_SHORT).show();
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
    }
}
