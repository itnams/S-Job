package com.example.s_job.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
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
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class User_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    private TextView tvUserName, tvEmail, tvPhone, tvAddress;
    EditText et_current_pass_com,et_new_pass_com,et_confirm_pass_com,et_Full_Name,et_Phone,et_addresss;
    Button btn_save_company,cancel_button;
    private DatabaseReference databaseRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    TextView tvlogout;
    ImageView imageLogout,imangeChangepass,yourFavorited,btnsetting,imageprofile;
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
        imageprofile = view.findViewById(R.id.imageprofile);
        tvlogout = view.findViewById(R.id.tvlogout);
        imangeChangepass = view.findViewById(R.id.imangeChangepass);
        btnsetting = view.findViewById(R.id.btnsetting);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot key : snapshot.getChildren()) {
                    Account account = key.getValue(Account.class);
                    if (login.passUser.equals(account.passWord)&&login.userLogin.equals(account.nameUser))
                    {
                        new LoadImge().execute(account.image);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getContext(), R.style.BottomSheetDialogTheme
                );

                View bottomSheetView = LayoutInflater.from(getContext())
                        .inflate(R.layout.bs_setting_profile, (LinearLayout) view.findViewById(R.id.bs_setting_profile));
//code o day
                et_Full_Name = bottomSheetView.findViewById(R.id.et_Full_Name);
                et_Phone = bottomSheetView.findViewById(R.id.et_Phone);
                et_addresss = bottomSheetView.findViewById(R.id.et_addresss);
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btn_save_profile).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_Full_Name.getText().toString().equals("")||et_Phone.getText().toString().equals("")||et_addresss.getText().toString().equals(""))
                        {
                            Toast.makeText(getContext(), "Update fail", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            mData.child("User").child(login.keyUser).child("address").setValue(et_addresss.getText().toString());
                            mData.child("User").child(login.keyUser).child("nameUser").setValue(et_Full_Name.getText().toString());
                            mData.child("User").child(login.keyUser).child("phone").setValue(et_Phone.getText().toString());
                            Toast.makeText(getContext(), "Update success", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.cancel();
                        }
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
                                et_Full_Name.setText(account.nameUser);
                                et_Phone.setText(account.phone);
                                et_addresss.setText(account.address);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
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

    private class LoadImge extends AsyncTask<String,Void, Bitmap> {
        Bitmap bitmapHinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageprofile.setImageBitmap(bitmap);

        }
    }
}