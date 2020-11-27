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


public class User_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    private TextView tvUserName, tvEmail, tvPhone, tvAddress;
private Account account;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__profile, container, false);
        init(view);


        return view;
    }

    private void init(View view) {
        //get data from activity :>
        Bundle bundle = getArguments();
        Log.d("banhdo", "onCreateView:"+ bundle);
        if(bundle != null){
            account = (Account) bundle.getSerializable("USER");
            Log.d("ahihi", "user "+ account.nameUser);
        }


        editProfile = view.findViewById(R.id.editProfile);
        changePassword = view.findViewById(R.id.change_Password);
        favoritedJob =  view.findViewById(R.id.favorited_jobs);

        tvUserName = view.findViewById(R.id.tvUser_name);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvAddress = view.findViewById(R.id.tvAddress);

        //create view data
        tvUserName.setText(account.nameUser);
        tvEmail.setText(account.email);
        tvPhone.setText(account.phone);
        tvAddress.setText(account.address);


//        posts = findViewById(R.id.your_Posts);
        logout = view.findViewById(R.id.logout);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        view.getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.bs_edit_profile, (LinearLayout)view.findViewById(R.id.bs_edit_profile_container));
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                 EditText edtuser_name = bottomSheetView.findViewById(R.id.edt_user_name);
                EditText edt_email = bottomSheetView.findViewById(R.id.edt_email);
                EditText edt_phone = bottomSheetView.findViewById(R.id.edt_phone);
                EditText edt_address = bottomSheetView.findViewById(R.id.edt_address);

                edtuser_name.setText(account.nameUser);
                edt_email.setText(account.email);
                edt_phone.setText(account.phone);
                edt_address.setText(account.address);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        view.getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.bs_change_password, (LinearLayout)view.findViewById(R.id.bs_change_password));
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        favoritedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), favoritedJobs.class);
                startActivity(intent);
            }
        });

//        posts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileUser.this, post.class);
//                startActivity(intent);
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }


}