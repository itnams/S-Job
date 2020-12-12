package com.example.s_job.Custom;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;

import java.util.ArrayList;

public class Custom_lv_DangTin extends BaseAdapter {
    Activity activity;
    ArrayList<PostForCompany> objects;


    static class Hoder {
        TextView tieude, deline, diachi, mota;
        ImageButton remove;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.item_lv_dangtin, viewGroup, false);
        Hoder hoder = new Hoder();
        hoder.tieude = view.findViewById(R.id.tv_tieude);
        hoder.deline = view.findViewById(R.id.tv_deline);
        hoder.diachi = view.findViewById(R.id.tv_diachi);
        hoder.mota = view.findViewById(R.id.tv_mota);
        hoder.remove = view.findViewById(R.id.imgbtn_remove);


        PostForCompany data = objects.get(i);

        hoder.tieude.setText(data.getTieuDe());
        hoder.deline.setText(data.getDeline());
        hoder.diachi.setText(data.getDiaChi());
        hoder.mota.setText(data.getMota().substring(0, data.getMota().length() / 2) + " ...");


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
                Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        
        hoder = (Hoder) view.getTag();
        view.setTag(hoder);
        return view;
    }
}
