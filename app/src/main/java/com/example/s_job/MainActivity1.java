package com.example.s_job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.s_job.Fragment.Company_Notification;
import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Fragment.User_Home;
import com.example.s_job.Fragment.User_Notification;
import com.example.s_job.Fragment.User_Profile;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity1 extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private SmoothBottomBar smoothBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conpany_home);
        smoothBottomBar = findViewById(R.id.bottomBar1);


        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fr_main1, new Company_Profile());
        fragmentTransaction.commit();

        smoothBottomBar.setOnItemSelectedListener(i->{
            switch (i){
                case 0:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.fr_main1, new Company_Profile());
                    fragmentTransaction.commit();

                    break;
                case 1:
                    //Toast.makeText(this, "fffffff", Toast.LENGTH_SHORT).show();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.fr_main1, new Company_Notification());
                   fragmentTransaction.commit();

                    break;

            }
        });
    }
}