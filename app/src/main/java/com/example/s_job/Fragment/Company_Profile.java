package com.example.s_job.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.s_job.Datacode.Account;
import com.example.s_job.MainActivity1;
import com.example.s_job.Model.Company;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;
import com.example.s_job.favoritedJobs;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;


public class Company_Profile extends Fragment {
    private LinearLayout editProfile, changePassword, favoritedJob, posts, logout;
    TextView name, email, sdt, diachi;
    ImageView img_company;
    static public boolean ngonNgu = true;

    static public Company company = new Company();

    //For bottomsheetView
    EditText fullname, mail, phone, address;
    ImageView imageView_sheet;
    Button btnUpLoad_sheet;
    Uri linkImage;

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
                data.setEmail((String) key.email.replace("@gmail.com", ""));
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
        db.myRef = db.database.getReference("Company");
        db.myRef.child("Additional-Company").child(MainActivity1.User).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    company.setImage(((HashMap) snapshot.getValue()).get("Image").toString());
                    //Toast.makeText(getActivity(), "" + company.getImage(), Toast.LENGTH_SHORT).show();
                    if (!company.getImage().equals("")) {
                        db.mStorageRef.child(company.getImage() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getActivity())
                                        .load(uri)
                                        .into(img_company);
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                dbFireBase db = new dbFireBase();
                db.myRef = db.database.getReference("Company");

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        view.getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(view.getContext())
                        .inflate(R.layout.bs_edit_profile, (LinearLayout) view.findViewById(R.id.bs_edit_profile_container));


//                EditText fullname, mail, phone, address;
//                ImageView imageView_sheet;
//                Button btnUpLoad_sheet;
                fullname = bottomSheetView.findViewById(R.id.edt_user_name);
                mail = bottomSheetView.findViewById(R.id.edt_email);
                phone = bottomSheetView.findViewById(R.id.edt_phone);
                address = bottomSheetView.findViewById(R.id.edt_address);
                imageView_sheet = bottomSheetView.findViewById(R.id.imgAvatar);
                btnUpLoad_sheet = bottomSheetView.findViewById(R.id.upload);

                if (company.getImage().isEmpty()) {
                    imageView_sheet.setImageResource(R.drawable.chamhoi_n);
                } else {

                    db.mStorageRef.child(company.getImage() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(getActivity())
                                    .load(uri)
                                    .into(imageView_sheet);
                        }
                    });

                }

                mail.setText(company.getEmail());
                phone.setText(company.getSdt());
                address.setText(company.getDiaChi());

                db.myRef.child("Additional-Company").child(company.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        company.setFullName(((HashMap) snapshot.getValue()).get("fullName").toString());
                        fullname.setText(company.getFullName());

                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                btnUpLoad_sheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(getString(R.string.titleUpload)).setMessage(getString(R.string.mesUpload))
                                .setNegativeButton(getString(R.string.BtnChupAnh), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(takePicture, 0);
                                        Toast.makeText(getActivity(), getString(R.string.BtnChupAnh), Toast.LENGTH_LONG).show();
                                    }
                                })
                                .setPositiveButton(getString(R.string.BtnThuVien), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Intent.ACTION_PICK,
                                                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                        intent.setType("image/*");
                                        intent.putExtra("crop", "true");
                                        intent.putExtra("scale", true);
                                        intent.putExtra("outputX", 256);
                                        intent.putExtra("outputY", 256);
                                        intent.putExtra("aspectX", 1);
                                        intent.putExtra("aspectY", 1);
                                        intent.putExtra("return-data", true);
                                        //intent.setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                                    }
                                })
                                .setNeutralButton(getString(R.string.thoat), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();

                    }
                });

                bottomSheetView.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpLoadData();
                        //Fix Send image to firebase
                        bottomSheetDialog.dismiss();

                        Toast.makeText(getContext(), "Change Infomation Success!!", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getContext(), getString(R.string.toastDoiPass), Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getContext(), getString(R.string.toastXacNhanPass), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext(), getString(R.string.toastMatKhauMacDinhPass), Toast.LENGTH_LONG).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        linkImage = data.getData();
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView_sheet.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == Activity.RESULT_OK && data != null) {

                        try {

                            final Uri imageUri = data.getData();
                            linkImage = imageUri;
                            final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            imageView_sheet.setImageBitmap(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
                    }


                    break;
            }

        }
    }

    //Click Save Profile in sheet
    void UpLoadData() {
//                EditText fullname, mail, phone, address;
//                ImageView imageView_sheet;
        company.setDiaChi(address.getText().toString());
        company.setEmail(mail.getText().toString());
        company.setSdt(phone.getText().toString());
        company.setFullName(fullname.getText().toString());

        File f = new File(String.valueOf(linkImage));
        String imageName = f.getName();
        company.setImage(imageName);
        new dbFireBase().UploadImgToFireBase(linkImage, imageName, getActivity());
        new dbFireBase().upDateCompanyFormCompany(company);
        new dbFireBase().upDateCompanyFormUser(company);
    }

}