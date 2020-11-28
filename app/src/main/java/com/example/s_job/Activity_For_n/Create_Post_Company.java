package com.example.s_job.Activity_For_n;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;

import java.util.Calendar;

public class Create_Post_Company extends AppCompatActivity {
    EditText tieuDe, deLine, mucLuong, bangCap, nganhNghe, soLuongTuyen, diaChi, moTa;
    ImageButton date, vitri;
    Button troVe, Luu;
    PostForCompany postForCompany = new PostForCompany();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post__company);
        setControl();
        setEvent();
    }

    private void setEvent() {
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mYear, mMonth, mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Create_Post_Company.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                deLine.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input()) {
                    SendToDataBase();
                    onBackPressed();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Create_Post_Company.this);
                    builder.setTitle("Thong Bao").setMessage("Have Input Is Empty!!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
                }


            }
        });
        vitri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Create_Post_Company.this, Goole_map.class);
                startActivity(intent);
            }
        });
    }

    boolean input() {
        if (tieuDe.getText().toString().isEmpty()
                && deLine.getText().toString().isEmpty()
                && mucLuong.getText().toString().isEmpty()
                && bangCap.getText().toString().isEmpty()
                && nganhNghe.getText().toString().isEmpty()
                && soLuongTuyen.getText().toString().isEmpty()
                && diaChi.getText().toString().isEmpty()
                && moTa.getText().toString().isEmpty()) {


            return false;
        } else {
            return true;
        }


    }

    private void SendToDataBase() {
        postForCompany.setCompany(Company_Profile.company);
        postForCompany.setTieuDe(tieuDe.getText().toString());
        postForCompany.setDeline(deLine.getText().toString());
        postForCompany.setMucLuong(mucLuong.getText().toString());
        postForCompany.setBangCap(bangCap.getText().toString());
        postForCompany.setNganhNghe(nganhNghe.getText().toString());
        postForCompany.setSoLuong(soLuongTuyen.getText().toString());
        postForCompany.setDiaChi(diaChi.getText().toString());
        postForCompany.setMota(moTa.getText().toString());
        new dbFireBase().NewPoserForCompany(postForCompany);
        Toast.makeText(this, "New Post Is Success!!", Toast.LENGTH_SHORT).show();
    }

    private void setControl() {
        tieuDe = findViewById(R.id.et_tieuDe);
        deLine = findViewById(R.id.et_deLine);
        mucLuong = findViewById(R.id.et_mucLuong);
        bangCap = findViewById(R.id.et_bangCap);
        nganhNghe = findViewById(R.id.et_nganhNghe);
        soLuongTuyen = findViewById(R.id.et_soLuongTuyen);
        diaChi = findViewById(R.id.et_diaChi);
        moTa = findViewById(R.id.et_moTa);
        //button
        date = findViewById(R.id.imgBtn_date);
        vitri = findViewById(R.id.imgBtn_vitri);
        troVe = findViewById(R.id.btn_trove);
        Luu = findViewById(R.id.btn_luu);


        //Sau nay remove
        diaChi.setText("123 Company");
    }
}