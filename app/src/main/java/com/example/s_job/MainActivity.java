package com.example.s_job;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.Fragment.User_Notification;
import com.example.s_job.Fragment.User_Profile;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
 private SmoothBottomBar smoothBottomBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        setControl();
        setEvent();


    }

    //Custom Void
    void ChuyenMangHinhFrament(int idSoure, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(idSoure, fragment);
        fragmentTransaction.commit();
    }

    //Set Event For Main
    private void setEvent() {
        ChuyenMangHinhFrament(R.id.fr_main, new User_Home());
        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelect(int i) {
                switch (i) {
                    case 0:
                        ChuyenMangHinhFrament(R.id.fr_main, new User_Home());
                        break;
                    case 1:
                        ChuyenMangHinhFrament(R.id.fr_main, new User_Notification());
                        break;
                    case 2:
                        ChuyenMangHinhFrament(R.id.fr_main, new User_Profile());
                        break;
                }
            }
        });

    }

    private void setControl() {
        smoothBottomBar = findViewById(R.id.bottomBar);

    }


}