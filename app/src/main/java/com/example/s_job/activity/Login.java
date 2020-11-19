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

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.GiaoDienAdmin;
import com.example.s_job.MainActivity;
import com.example.s_job.MainActivity1;
import com.example.s_job.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Login extends AppCompatActivity {
private TextView fogotPW;
private TextView Signup;
private Button btnLogin;
private EditText edtuser, edtpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fogotPW = findViewById(R.id.resetpw);
        Signup = findViewById(R.id.sigup);
        btnLogin = findViewById(R.id.btnLogin);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
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
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtuser.getText().toString().equals("admin") && edtpass.getText().toString().equals("long0077") ){
                    Intent intent = new Intent(getApplicationContext(), GiaoDienAdmin.class);
                    startActivity(intent);

                }
                else if(edtuser.getText().toString().equals("nguoidung") && edtpass.getText().toString().equals("tuancute") )
                {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                 else if(edtuser.getText().toString().equals("tx2") && edtpass.getText().toString().equals("1") )
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Login.this,"Mật khẩu tài khoản không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
