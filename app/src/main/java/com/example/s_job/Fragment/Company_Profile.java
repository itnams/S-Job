package com.example.s_job.Fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.s_job.MainActivity1;
import com.example.s_job.Model.Company;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;
import com.example.s_job.favoritedJobs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class Company_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    TextView name, email, sdt, diachi;
    ImageView img_company;


    Company company = new Company();

    //For bottomsheetView
    EditText fullname, mail, phone, address;
    ImageView imageView_sheet;
    Button btnUpLoad_sheet;

    //---------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_company_profile, container, false);

        //Nhan
        name = view.findViewById(R.id.tv_NameCompany);
        email = view.findViewById(R.id.tv_Email_Company);
        sdt = view.findViewById(R.id.tv_std_Company);
        diachi = view.findViewById(R.id.tv_diaChi_Company);
        img_company = view.findViewById(R.id.image_company);
        //----------------------------
        changeTextView();
        init(view);


        return view;
    }

    private void changeTextView() {
        dbFireBase db = new dbFireBase();
        db.myRef = db.database.getReference("User");
        db.myRef.child(MainActivity1.User).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account key = dataSnapshot.getValue(Account.class);

                Company data = new Company();
                data.setPass((String) key.passWord);
                data.setNameCompany((String) key.nameUser);
                data.setSdt((String) key.phone);
                data.setEmail((String) key.email);
                data.setDiaChi((String) key.address);
                company = data;
                // Toast.makeText(getContext(), company.getDiaChi(), Toast.LENGTH_LONG).show();
                if (company.getImage().equals("")) {
                    img_company.setImageResource(R.drawable.chamhoi_n);
                } else {
                    Toast.makeText(getContext(), company.getImage(), Toast.LENGTH_LONG).show();
                }
                name.setText(company.getNameCompany());
                email.setText(company.getEmail());
                sdt.setText(company.getSdt());
                diachi.setText(company.getDiaChi());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void init(View view) {
        if (company == null) {
            Toast.makeText(getContext(), "Không Có Dữ Liệu!!", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
        editProfile = view.findViewById(R.id.editProfile);
        changePassword = view.findViewById(R.id.change_Password);
        favoritedJob = view.findViewById(R.id.favorited_jobs);

        logout = view.findViewById(R.id.logout);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        view.getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.bs_edit_profile, (LinearLayout) view.findViewById(R.id.bs_edit_profile_container));


//                EditText fullname, mail, phone, address;
//                ImageView imageView_sheet;
//                Button btnUpLoad_sheet;
                fullname = bottomSheetView.findViewById(R.id.et_FullName);
                mail = bottomSheetView.findViewById(R.id.et_Email);
                phone = bottomSheetView.findViewById(R.id.et_Phone);
                address = bottomSheetView.findViewById(R.id.et_address);
                imageView_sheet = bottomSheetView.findViewById(R.id.imgAvatar);
                btnUpLoad_sheet = bottomSheetView.findViewById(R.id.upload);

                if (company.getImage().isEmpty()) {
                    imageView_sheet.setImageResource(R.drawable.chamhoi_n);
                } else {

                }

                mail.setText(company.getEmail());
                phone.setText(company.getSdt());
                address.setText(company.getDiaChi());
                fullname.setText(company.getFullName());

                btnUpLoad_sheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Go To The Gallary, Libary, Picture!!", Toast.LENGTH_SHORT).show();
                    }
                });

                bottomSheetView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpLoadData();
                    }
                });

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

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new sheetdialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        view.getContext(), R.style.BottomSheetDialogTheme
                );
                //set view sheetdialog
                View bottomSheetView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.bs_change_password, (LinearLayout) view.findViewById(R.id.bs_change_password));

                //Anh xạ:
                EditText currentpass = bottomSheetView.findViewById(R.id.et_current_pass_com);
                EditText newPass = bottomSheetView.findViewById(R.id.et_new_pass_com);
                EditText ComfirmPass = bottomSheetView.findViewById(R.id.et_confirm_pass_com);

                //------------
                bottomSheetView.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btn_save_company).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (company.getPass().equals(currentpass.getText().toString())) {
                            if (newPass.getText().toString().equals(ComfirmPass.getText().toString())) {
                                company.setPass(newPass.getText().toString());
                                new dbFireBase().upDateCompanyFormUser(company);
                                bottomSheetDialog.dismiss();
                                Toast.makeText(getContext(), "Change Pass Success!!!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), "New Pass And Comfirm Pass is Not Equals!!!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Current Pass Error!!!", Toast.LENGTH_LONG).show();
                        }
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
                Toast.makeText(view.getContext(), "LogOut", Toast.LENGTH_LONG).show();
                getActivity().finish();

            }
        });
    }

    //Click Save Profile in sheet
    void UpLoadData() {
//                EditText fullname, mail, phone, address;
//                ImageView imageView_sheet;
        new dbFireBase().upDateCompanyFormCompany(company);

    }

}