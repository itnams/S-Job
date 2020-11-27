package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.example.s_job.activity.Login;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiaoDienAdmin extends AppCompatActivity {
    TextView tvqltk;
    TextView tvcnyc;
    TextView tvweb;
    Login login;
    LinearLayout changePassword;
    DatabaseReference mData;
    bs_change_password bs_change_password1;

    TextView tenAdmin, emailAdmin, sdtAdmin, diaChiAmdin;
    TextView tvlogout;
    public static String usernamekey;
    Account account = new Account();
    //chilrent in sheetview
    EditText current, newPass, comfirmpass;

    //-------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_admin);
        tvqltk = findViewById(R.id.tvqltk);
        tvlogout = findViewById(R.id.tvlogout);
        tvcnyc = findViewById(R.id.tvcnyc);
        tvweb = findViewById(R.id.tvweb);
        tenAdmin = findViewById(R.id.tenAdmin);
        emailAdmin = findViewById(R.id.emailAdmin);
        changePassword = findViewById(R.id.change_Password);
        sdtAdmin = findViewById(R.id.sdtAdmin);
        diaChiAmdin = findViewById(R.id.diaChiAdmin);
        mData = FirebaseDatabase.getInstance().getReference();

        mData.child("User").child(Login.tentaikhoanAdmin).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    account = snapshot.getValue(Account.class);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Account account = dataSnapshot.getValue(Account.class);
                String tenAdmin1 = account.nameUser;
                String emailAdmin1 = account.email;
                String sdtAdmin1 = account.phone;
                String diaChiAdmin1 = account.address;
//                if(login.trangThai == "Khóa")
//                {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GiaoDienAdmin.this);
//                    builder1.setTitle("Thông báo !");
//                    builder1.setMessage("Tài khoản của bạn đang bị khoá vui lòng liên hệ Admin để biết thêm chi tiết ! Click Đồng ý để quay lại yrang đăng nhập ! ");
//                    builder1.setCancelable(true);
//                    builder1.setPositiveButton("Đồng ý",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    Intent intent = new Intent(GiaoDienAdmin.this,Login.class);
//                                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                                }
//                            });
//                    AlertDialog alert11 = builder1.create();
//                    alert11.show();
//                }
                if (login.tentaikhoanAdmin.equals(tenAdmin1)) {
                    tenAdmin.setText(tenAdmin1);
                    emailAdmin.setText(emailAdmin1);
                    sdtAdmin.setText(sdtAdmin1);
                    diaChiAmdin.setText(diaChiAdmin1);
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
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        GiaoDienAdmin.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(GiaoDienAdmin.this)
                        .inflate(R.layout.bs_change_passwordadmin, (LinearLayout) findViewById(R.id.bs_change_password));
//code o day
                current = bottomSheetView.findViewById(R.id.curenpass);
                newPass = bottomSheetView.findViewById(R.id.newpass);
                comfirmpass = bottomSheetView.findViewById(R.id.comfimpass);
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.luupass).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (account.getPassWord().equals(current.getText().toString())) {
                            if (newPass.getText().toString().equals(comfirmpass.getText().toString())) {
                                account.setPassWord(newPass.getText().toString());
                                mData.child("User").child(account.nameUser).setValue(account);
                                bottomSheetDialog.dismiss();
                            } else {
                                Toast.makeText(GiaoDienAdmin.this, "Mật Khẩu Không Khớp!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GiaoDienAdmin.this, "Mật Khẩu Hiện Tại Không Đúng!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        tvqltk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamekey = emailAdmin.getText().toString().replace("@gmail.com", "");

                Intent intent = new Intent(GiaoDienAdmin.this, GiaoDienQLTK.class);
                startActivity(intent);
            }
        });
        tvcnyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this, GiaoDienChapNhanYCDN.class);
                startActivity(intent);
            }
        });
        tvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this, GiaoDienTrangTuyenDung.class);
                startActivity(intent);
            }
        });

    }
}