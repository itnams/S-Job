package com.example.s_job.Custom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.s_job.Model.PostForCompany;
import com.example.s_job.R;

import java.util.ArrayList;

public class Custom_lv_DangTin extends BaseAdapter {
    Activity activity;
    ArrayList<PostForCompany> objects;

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

        return view;
    }
}
