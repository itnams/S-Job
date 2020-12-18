package com.example.s_job.Activity_For_n;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;
import com.example.s_job.favoritedJobs;

import java.util.Calendar;
import java.util.TimeZone;

public class Create_Post_Company extends AppCompatActivity {
    EditText tieuDe, deLine, mucLuong, bangCap, nganhNghe, soLuongTuyen, diaChi, moTa;
    ImageButton date, vitri;
    Button troVe, Luu;
    Spinner tinhThanh;
    PostForCompany postForCompany = new PostForCompany();
    public static String[] TinhThanhs = {"An Giang",
            "Bà Rịa - Vũng Tàu", "Bắc Giang",
            "Bắc Kạn", "Bạc Liêu",
            "Bắc Ninh", "Bến Tre",
            "Bình Định", "Bình Dương",
            "Bình Phước", "Bình Thuận",
            "Cà Mau", "Cao Bằng",
            "Đắk Lắk", "Đắk Nông",
            "Điện Biên", "Đồng Nai",
            "Đồng Tháp", "Gia Lai",
            "Hà Giang", "Hà Nam",
            "Hà Tĩnh", "Hải Dương",
            "Hậu Giang", "Hòa Bình",
            "Hưng Yên", "Khánh Hòa",
            "Kiên Giang", "Kon Tum",
            "Lai Châu", "Lâm Đồng",
            "Lạng Sơn", "Lào Cai",
            "Long An", "Nam Định",
            "Nghệ An", "Ninh Bình",
            "Ninh Thuận", "Phú Thọ",
            "Quảng Bình", "Quảng Nam",
            "Quảng Ngãi", "Quảng Ninh",
            "Quảng Trị", "Sóc Trăng",
            "Sơn La", "Tây Ninh",
            "Thái Bình", "Thái Nguyên",
            "Thanh Hóa", "Thừa Thiên Huế",
            "Tiền Giang", "Trà Vinh",
            "Tuyên Quang", "Vĩnh Long",
            "Vĩnh Phúc", "Yên Bái",
            "Phú Yên", "Cần Thơ",
            "Đà Nẵng", "Hải Phòng",
            "Hà Nội", "TP HCM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post__company);
        setControl();
        setEvent();
    }


    private void setEvent() {
        tinhThanh.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, TinhThanhs));
        tinhThanh.setSelection(0);
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        deLine.setText("" + currentDay + '/' + currentMonth + '/' + currentYear);

        if (getIntent() != null && getIntent().getExtras().getBoolean("chitiet")) {
            PostForCompany postForCompany = favoritedJobs.posts.get(getIntent().getExtras().getInt("vitri"));
            tieuDe.setText(postForCompany.getTieuDe());
            deLine.setText(postForCompany.getDeline());
            mucLuong.setText(postForCompany.getMucLuong());
            bangCap.setText(postForCompany.getBangCap());
            nganhNghe.setText(postForCompany.getNganhNghe());
            soLuongTuyen.setText(postForCompany.getSoLuongTuyen());
            diaChi.setText(postForCompany.getDiaChi());
            moTa.setText(postForCompany.getMota());
            for (int i = 0; i < TinhThanhs.length; i++) {
                if (TinhThanhs[i].equals(postForCompany.getTinhThanh())) {
                    tinhThanh.setSelection(i);
                    break;
                }
            }
            // Toast.makeText(this, "" + postForCompany.getSoLuongTuyen(), Toast.LENGTH_LONG).show();
            Luu.setEnabled(false);
        }

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

                                deLine.setText(""+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

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
                    builder.setTitle(getString(R.string.thongBao)).setMessage(getResources().getString(R.string.inputText)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
        troVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    boolean input() {
        if (tieuDe.getText().toString().isEmpty()) {
            return false;
        } else if (mucLuong.getText().toString().isEmpty()) {
            return false;
        } else if (bangCap.getText().toString().isEmpty()) {
            return false;
        } else if (nganhNghe.getText().toString().isEmpty()) {
            return false;
        } else if (soLuongTuyen.getText().toString().isEmpty()) {
            return false;
        } else if (moTa.getText().toString().isEmpty()) {
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
        postForCompany.setSoLuongTuyen(soLuongTuyen.getText().toString());
        postForCompany.setDiaChi(diaChi.getText().toString());
        postForCompany.setMota(moTa.getText().toString());
        postForCompany.setTinhThanh(TinhThanhs[tinhThanh.getSelectedItemPosition()]);
        new dbFireBase().NewPoserForCompany(postForCompany);
        Toast.makeText(this, getString(R.string.taoBai), Toast.LENGTH_SHORT).show();
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
        tinhThanh = findViewById(R.id.spn_tinhThanh);

        //Sau nay remove
        diaChi.setText("123 Company");
    }
}