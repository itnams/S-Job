package com.example.s_job;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.s_job.Fragment.Company_Notification;
import com.example.s_job.Fragment.Company_Profile;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity1 extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private SmoothBottomBar smoothBottomBar;
    static public String User = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conpany_home);
        //login thanh cong, chuyển intent qua màng hình inten có 1 dữ liệu , intent -> username
        if (getIntent() != null) {
            User = getIntent().getStringExtra("email");
        } else {
            Toast.makeText(this, getString(R.string.dulieu), Toast.LENGTH_SHORT).show();
        }
        setControl();
        setEvent();


    }
    //Custom event
    void ChuyenMangHinhFrament(int idSoure, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(idSoure, fragment);
        fragmentTransaction.commit();
    }
    //set Event for Main
    private void setEvent() {


        ChuyenMangHinhFrament(R.id.fr_main1, new Company_Profile());

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int i) {
                switch (i) {
                    case 0:
                        ChuyenMangHinhFrament(R.id.fr_main1, new Company_Profile());


                        break;
                    case 1:
                        ChuyenMangHinhFrament(R.id.fr_main1, new Company_Notification());


                        break;

                }
            }
        });

    }

    private void setControl() {
        smoothBottomBar = findViewById(R.id.bottomBar1);
    }
}