package com.example.s_job;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.s_job.activity.Login;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.DataFormatException;

public class bs_change_password extends AppCompatActivity {

    public static EditText currentpass, newpass, comfrimpass;
    public static Button btnLuupass;
    Login login;
    GiaoDienAdmin giaoDienAdmin;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bs_change_passwordadmin);
        mData = FirebaseDatabase.getInstance().getReference();
        currentpass = findViewById(R.id.curenpass);
        newpass = findViewById(R.id.newpass);
        comfrimpass = findViewById(R.id.comfimpass);
        btnLuupass = findViewById(R.id.luupass);
        btnLuupass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.curentpass.equals(currentpass) && newpass.equals(comfrimpass))
                {
                    mData.child("User").child(giaoDienAdmin.usernamekey).child("passWord").setValue(newpass);
                    Toast.makeText(bs_change_password.this,"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}