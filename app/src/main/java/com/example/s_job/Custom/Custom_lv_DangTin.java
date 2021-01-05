package com.example.s_job.Custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.s_job.Activity_For_n.Create_Post_Company;
import com.example.s_job.MainActivity1;
import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Custom_lv_DangTin extends BaseAdapter {
    Activity activity;
    ArrayList<PostForCompany> objects;


    static class Hoder {
        TextView tieude, deline, diachi, mota, rating, commnet;
        ImageButton remove, detail;
    }


    public Custom_lv_DangTin(Activity activity, ArrayList<PostForCompany> objects) {
        this.activity = activity;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.item_lv_dangtin, viewGroup, false);
        Hoder hoder = new Hoder();
        hoder.tieude = view.findViewById(R.id.tv_tieude);
        hoder.deline = view.findViewById(R.id.tv_deline);
        hoder.diachi = view.findViewById(R.id.tv_diachi);
        hoder.mota = view.findViewById(R.id.tv_mota);
        hoder.rating = view.findViewById(R.id.tv_rating);
        hoder.commnet = view.findViewById(R.id.tv_comment);
        hoder.remove = view.findViewById(R.id.imgbtn_remove);
        hoder.detail = view.findViewById(R.id.btn_edit);


        PostForCompany data = objects.get(position);

        dbFireBase db = new dbFireBase();

        Hoder finalHoder = hoder;
        db.myRef.child("Post-Company")
                .child(MainActivity1.User)
                .child(data.getKey())
                .child("Comment")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int ratingH = 0;
                        int comment = 0;
                        if (snapshot.exists()) {
                            finalHoder.commnet.setText("" + snapshot.getChildrenCount());
                            for (DataSnapshot el : snapshot.getChildren()) {
                                ratingH += Integer.parseInt(((HashMap) el.getValue()).get("ratting").toString());
                            }
                            comment = Integer.parseInt("" + snapshot.getChildrenCount());
                            finalHoder.rating.setText("" + (ratingH / comment));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        hoder.tieude.setText(data.getTieuDe());
        hoder.deline.setText(data.getDeline());
        hoder.diachi.setText(data.getDiaChi());
        hoder.mota.setText(data.getMota().length() > 20 ? data.getMota().substring(0, 20) + " ..." : data.getMota());


        hoder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Thông Báo!!")
                        .setMessage("Bạn Chắc Chắn Có Muốn Xoá " + data.getTieuDe() + " ?")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new dbFireBase().removePost(data, activity);

                            }
                        })
                        .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();

            }
        });
        hoder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Create_Post_Company.class);
                intent.putExtra("chitiet", true);
                intent.putExtra("vitri", position);
                activity.startActivity(intent);
            }
        });
        hoder = (Hoder) view.getTag();
        view.setTag(hoder);
        return view;
    }
}
