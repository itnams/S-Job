package com.example.s_job;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Datacode.Account;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChiTietTaiKhoan extends AppCompatActivity {
    GiaoDienQLTK giaoDienQLTK;
    EditText edttentaikhoan, edtmatkhau, edtdouutien, edttrangthai;
    Button btnKhoa, btnluu, btnmokhoa;
    DatabaseReference mData;
    String douutienrdo = "Thấp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        edttentaikhoan = findViewById(R.id.edttentaikhoan);
        edtmatkhau = findViewById(R.id.edtmatkhau);
        edttrangthai = findViewById(R.id.edttrangthai);
        btnKhoa = findViewById(R.id.btnkhoa);
        btnluu = findViewById(R.id.btnluu);
        btnmokhoa = findViewById(R.id.btnmokhoa);
        edttrangthai.setEnabled(false);
        edttentaikhoan.setText(giaoDienQLTK.nameTK1);
        edtmatkhau.setText(giaoDienQLTK.passwordtk1);
        edttrangthai.setText(giaoDienQLTK.trangthai1);
        RadioGroup radioGroup = findViewById(R.id.rdoGroup);
        RadioButton radioButton1 = findViewById(R.id.rdothap);
        RadioButton radioButton2 = findViewById(R.id.rdocao);
        //-----Long
        if(giaoDienQLTK.douutien1.equals("Thấp"))
        {
            radioButton1.setChecked(true);
        }else {
            radioButton2.setChecked(true);
        }

        //-----Long
        btnmokhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ChiTietTaiKhoan.this);
                builder1.setTitle("Vui lòng lựa chọn !");
                builder1.setMessage("Ban muon mo khoa tai khoan " + giaoDienQLTK.emailkeyword);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Refuse",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Accept",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mData = FirebaseDatabase.getInstance().getReference();
                                mData.child("User").child(giaoDienQLTK.emailkeyword).child("trangthai").setValue("Bình thường");
                                Toast.makeText(ChiTietTaiKhoan.this, "Mo Khoa thanh cong", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ChiTietTaiKhoan.this);
                builder1.setTitle("Vui lòng lựa chọn !");
                builder1.setMessage("Bạn muốn lưu !");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Refuse",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Accept",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if(radioButton1.isChecked())
                                {
                                    douutienrdo ="Thấp";
                                }
                                else
                                {
                                    douutienrdo = "Cao";
                                }
                                mData = FirebaseDatabase.getInstance().getReference();
                                mData.child("User").child(giaoDienQLTK.emailkeyword).child("nameUser").setValue(edttentaikhoan.getText().toString());
                                mData.child("User").child(giaoDienQLTK.emailkeyword).child("passWord").setValue(edtmatkhau.getText().toString());
                                mData.child("User").child(giaoDienQLTK.emailkeyword).child("douutien").setValue(douutienrdo);
                                Toast.makeText(ChiTietTaiKhoan.this, "Luu thanh cong", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        btnKhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(ChiTietTaiKhoan.this);
                builder1.setTitle("Vui lòng lựa chọn !");
                builder1.setMessage("Bạn muốn xóa tài khoản này !" + giaoDienQLTK.emailkeyword);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Từ Chối",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton("Đồng ý",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mData = FirebaseDatabase.getInstance().getReference();
                                mData.child("User").child(giaoDienQLTK.emailkeyword).child("trangthai").setValue("Khoa");
                                Toast.makeText(ChiTietTaiKhoan.this, "Khóa thành công", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
}