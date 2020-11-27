package com.example.s_job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

import java.io.Serializable;

public class Login extends AppCompatActivity {
private TextView fogotPW;
private TextView Signup;
private Button btnLogin;
private EditText edtuser, edtpass;
public static String tentaikhoanAdmin;
public static  String trangThai;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference mData;
int n=0;
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
                        Account account = dataSnapshot.getValue(Account.class);
                        String nameUser = account.nameUser;
                        String passWord = account.passWord;
                        String position = account.position;
                        trangThai = account.trangthai;
                        if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("Admin")){
                            Intent intent = new Intent(getApplicationContext(), GiaoDienAdmin.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            tentaikhoanAdmin = edtuser.getText().toString();
                            n = 1;
                        }
                        else if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("User") )
                        {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("USER", (Serializable) account);//sent data to userHOme
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            n =1;
                        }
                        else if(edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("Company"))
                        {
                            Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            n =1;
                        }
                        else
                        {
                            n=2;
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
                if(n == 2){
                    Toast.makeText(Login.this,"Đăng nhập không thành công !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
