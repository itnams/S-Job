package com.example.s_job.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.GiaoDienAdmin;
import com.example.s_job.MainActivity;
import com.example.s_job.MainActivity1;
import com.example.s_job.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView fogotPW;
    public static  String userLogin;
    private TextView Signup;
    private Button btnLogin;
    private EditText edtuser, edtpass;
    public static String passadmin;
    DatabaseReference mData;
    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        fogotPW = findViewById(R.id.resetpw);
        Signup = findViewById(R.id.sigup);
        btnLogin = findViewById(R.id.btnLogin);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        mData = FirebaseDatabase.getInstance().getReference();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, GiaoDienAdmin.class));
            finish();
        }
        fogotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        Login.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bs_reset_password, (LinearLayout) findViewById(R.id.bs_change_password));
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                EditText edtmailrs = bottomSheetView.findViewById(R.id.edtmailrs);
                EditText passrs = bottomSheetView.findViewById(R.id.edtnewpassrs);
                EditText comfirmrs = bottomSheetView.findViewById(R.id.edtcomfimpassrs);
                bottomSheetView.findViewById(R.id.btnreset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    for(DataSnapshot key : snapshot.getChildren())
                                    {
                                        Account account = key.getValue(Account.class);
                                        String mail = account.email;
                                        if(edtmailrs.getText().toString().isEmpty() || passrs.getText().toString().isEmpty() || comfirmrs.getText().toString().isEmpty())
                                        {
                                            Toast.makeText(Login.this, "Vui lòng nhập thông tin !", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            if(mail.equals(edtmailrs.getText().toString()))
                                            {
                                                if(passrs.getText().toString().equals(comfirmrs.getText().toString()))
                                                {
                                                    account.setPassWord(passrs.getText().toString());
                                                    mData.child("User").child(account.email.replace("@gmail.com","")).setValue(account);
                                                    Toast.makeText(Login.this, "Đổi Mật Khẩu Thành Công !", Toast.LENGTH_SHORT).show();
                                                    bottomSheetDialog.dismiss();
                                                }else {
                                                    Toast.makeText(Login.this, "Mật khẩu không khớp !", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                            else {
                                                Toast.makeText(Login.this, "Không tìm thấy tài khoản !", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtuser.getText().toString();
                final String password = edtpass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edtpass.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login.this, GiaoDienAdmin.class);
                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    passadmin = edtpass.getText().toString();
                                    finish();
                                }
                            }
                        });

                mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //Nhan Fix
                        if (snapshot.exists()) {
                            for (DataSnapshot key : snapshot.getChildren()) {
                                Account account = key.getValue(Account.class);
                                String nameUser = account.nameUser;
                                userLogin = account.nameUser;
                                String passWord = account.passWord;
                                String position = account.position;
                                String trangThai1 = account.trangthai;
                                String email = account.email;
                                if (edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("User")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    if(trangThai1.equals("Khóa"))
                                    {
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                                        builder1.setTitle("-------Thông Báo-------");
                                        builder1.setMessage("Tài khoản của bạn đang bị khóa vui lòng liên hệ Admin với số điện thoại 0332175559 để biết thêm chi tiết và hỗ trợ !");
                                        builder1.setCancelable(true);
                                        builder1.setPositiveButton("Quay về",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                        builder1.setNegativeButton("Thoát Login",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        finish();
                                                    }
                                                });
                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Đăng Nhập Thành Công !", Toast.LENGTH_SHORT).show();
                                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    }
                                    return;

                                } else if (edtuser.getText().toString().equals(nameUser) && edtpass.getText().toString().equals(passWord) && position.equals("Company")) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity1.class);
                                    if(trangThai1.equals("Khóa"))
                                    {
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                                        builder1.setTitle("---Thông Báo---");
                                        builder1.setMessage("Tài khoản của bạn đang bị khóa vui lòng liên hệ Admin với số điện thoại 0332175559 để biết thêm chi tiết và hỗ trợ !");
                                        builder1.setCancelable(true);
                                        builder1.setPositiveButton("Quay về",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                        builder1.setNegativeButton("Thoát Login",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        finish();
                                                    }
                                                });
                                        AlertDialog alert11 = builder1.create();
                                        alert11.show();
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Đăng Nhập Thành Công !", Toast.LENGTH_SHORT).show();
                                        intent.putExtra("email", email.replace("@gmail.com", ""));
                                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    }
                                    return;
                                }
                            }
                        }
                        //---------
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

}