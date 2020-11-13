package com.example.s_job;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.Fragment.User_Notification;
import com.example.s_job.Fragment.User_Profile;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
 private SmoothBottomBar smoothBottomBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        smoothBottomBar = findViewById(R.id.bottomBar);



        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fr_main, new User_Home());
        fragmentTransaction.commit();

        smoothBottomBar.setOnItemSelectedListener(i->{
            switch (i){
                case 0:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.fr_main, new User_Home());
                    fragmentTransaction.commit();

                    break;
                case 1:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.fr_main, new User_Notification());
                    fragmentTransaction.commit();

                    break;
                case 2:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.fr_main, new User_Profile());
                    fragmentTransaction.commit();

                    break;
            }
        });



//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add("TP.HCM");
//        arrayList.add("Hà Nội");
//        arrayList.add("Đà Nẵng");
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,arrayList);
//        sprDiaDiem.setAdapter(arrayAdapter);

    }

//    private void init() {
//        editProfile = findViewById(R.id.editProfile);
//        changePassword = findViewById(R.id.change_Password);
//        favoritedJob =  findViewById(R.id.favorited_jobs);
//        posts = findViewById(R.id.your_Posts);
//        logout = findViewById(R.id.logout);
//
//
//
//
//
//
//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        MainActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(R.layout.bs_edit_profile, (LinearLayout)findViewById(R.id.bs_edit_profile_container));
//                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        changePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        MainActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(R.layout.bs_change_password, (LinearLayout)findViewById(R.id.bs_change_password));
//                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        favoritedJob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, favoritedJobs.class);
//                startActivity(intent);
//            }
//        });
//
//        posts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, post.class);
//                startActivity(intent);
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Logout nè >_<", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//    }

}