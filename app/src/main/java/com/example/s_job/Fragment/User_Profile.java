package com.example.s_job.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.s_job.Datacode.Account;
import com.example.s_job.R;
import com.example.s_job.favoritedJobs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class User_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    private TextView tvUserName, tvEmail, tvPhone, tvAddress;

    private DatabaseReference databaseRef;
private Account account;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
    }


}