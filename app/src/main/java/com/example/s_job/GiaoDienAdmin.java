package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.s_job.Datacode.Account;
import com.example.s_job.activity.Login;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GiaoDienAdmin extends AppCompatActivity {
    TextView tvqltk;
    TextView tvcnyc;
    TextView tvweb;
    private FirebaseAuth mAuth;
    LinearLayout changePassword;
    DatabaseReference mData;
    TextView tenAdmin, emailAdmin;
    TextView tvlogout;
    Login login;
    public static String usernamekey;
    Account account = new Account();
    EditText newPass,currentpass;
    //-------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_admin);
        tvqltk = findViewById(R.id.tvqltk);
        mAuth = FirebaseAuth.getInstance();
        tvlogout = findViewById(R.id.tvlogout);
        tvcnyc = findViewById(R.id.tvcnyc);
        tvweb = findViewById(R.id.tvweb);
        tenAdmin = findViewById(R.id.tenAdmin);
        tenAdmin.setText(mAuth.getCurrentUser().getEmail().replace("@gmail.com",""));
        emailAdmin = findViewById(R.id.emailAdmin);
        emailAdmin.setText(mAuth.getCurrentUser().getEmail());
        changePassword = findViewById(R.id.change_Password);
        mData = FirebaseDatabase.getInstance().getReference();
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        GiaoDienAdmin.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(GiaoDienAdmin.this)
                        .inflate(R.layout.bs_change_passwordadmin, (LinearLayout) findViewById(R.id.bs_change_password));
                //code o day
                newPass = bottomSheetView.findViewById(R.id.newpass);
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.luupass).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentpass = bottomSheetView.findViewById(R.id.currentpass);
                        if (newPass.getText().toString().isEmpty() && currentpass.getText().toString().isEmpty()) {
                            Toast.makeText(GiaoDienAdmin.this, "Vui lòng nhập thông tin !", Toast.LENGTH_SHORT).show();

                        } else {
                            if(currentpass.getText().toString().equals(login.passadmin))
                            {
                                mAuth.getCurrentUser().updatePassword(newPass.getText().toString());
                                Toast.makeText(GiaoDienAdmin.this, "Đổi Mật Khẩu Thành Công !", Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(GiaoDienAdmin.this, Login.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                            else {
                               currentpass.setError("Mật khẩu cũ không đúng");
                            }
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
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        tvcnyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this, GiaoDienChapNhanYCDN.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        tvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(GiaoDienAdmin.this, Login.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
        tvweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiaoDienAdmin.this, GiaoDienTrangTuyenDung.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }
}