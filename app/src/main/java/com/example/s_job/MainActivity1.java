package com.example.s_job;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.s_job.Datacode.Account;
import com.example.s_job.Fragment.Company_Notification;
import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Model.Company;
import com.example.s_job.db_firebase.dbFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity1 extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private SmoothBottomBar smoothBottomBar;
    static public String User = "company";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conpany_home);
        //login thanh cong, chuyển intent qua màng hình inten có 1 dữ liệu , intent -> username

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