package com.example.s_job.Fragment;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.s_job.Datacode.Account;
import com.example.s_job.GiaoDienAdmin;
import com.example.s_job.R;
import com.example.s_job.YourFavorited;
import com.example.s_job.activity.Login;
import com.example.s_job.favoritedJobs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class User_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    private TextView tvUserName, tvEmail, tvPhone, tvAddress;
    EditText et_current_pass_com,et_new_pass_com,et_confirm_pass_com;
    Button btn_save_company,cancel_button;
    private DatabaseReference databaseRef;
    TextView tvlogout;
    ImageView imageLogout,imangeChangepass,yourFavorited;
    Login login;
    DatabaseReference mData;
    private Account account;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        tvAddress = view.findViewById(R.id.tvAddress);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvUserName = view.findViewById(R.id.tvUser_name);
        imageLogout = view.findViewById(R.id.imageLogout);
        tvUserName.setText(login.userLogin);
        yourFavorited = view.findViewById(R.id.yourFavorited);
        tvlogout = view.findViewById(R.id.tvlogout);
        imangeChangepass = view.findViewById(R.id.imangeChangepass);

        imangeChangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getContext(), R.style.BottomSheetDialogTheme
                );

                View bottomSheetView = LayoutInflater.from(getContext())
                        .inflate(R.layout.bs_change_password, (LinearLayout) view.findViewById(R.id.bs_change_password));
//code o day
                et_current_pass_com = bottomSheetView.findViewById(R.id.et_current_pass_com);
                et_confirm_pass_com = bottomSheetView.findViewById(R.id.et_confirm_pass_com);
                et_new_pass_com = bottomSheetView.findViewById(R.id.et_new_pass_com);
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btn_save_company).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      if (et_current_pass_com.getText().toString().equals("")||et_confirm_pass_com.getText().toString().equals("")||et_new_pass_com.getText().toString().equals(""))
                        {
                            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                      else
                      {
                          if (et_current_pass_com.getText().toString().equals(login.passUser))
                          {
                            if (et_new_pass_com.getText().toString().equals(et_confirm_pass_com.getText().toString()))
                            {
                                mData.child("User").child(login.keyUser).child("passWord").setValue(et_new_pass_com.getText().toString());
                                Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext().getApplicationContext(),Login.class);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                          }
                          else
                          {
                              Toast.makeText(getContext(), "Nhập sai mật khẩu", Toast.LENGTH_SHORT).show();
                          }
                      }
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        yourFavorited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), YourFavorited.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
       imageLogout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getContext().getApplicationContext(),Login.class);
               startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
           }
       });
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot key : snapshot.getChildren()) {
                Account account = key.getValue(Account.class);
                if (login.passUser.equals(account.passWord)&&login.userLogin.equals(account.nameUser))
                {
                    tvAddress.setText(account.address);
                    tvEmail.setText(account.email);
                    tvPhone.setText(account.phone);
                }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}