package com.example.s_job.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.s_job.R;

import java.util.ArrayList;

public class Custom_lv_yeucau extends BaseAdapter {
    Context context;
    ArrayList<Object> ojs;

    public Custom_lv_yeucau(Context context, ArrayList<Object> ojs) {
        this.context = context;
        this.ojs = ojs;
    }

    @Override
    public int getCount() {
        return ojs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = LayoutInflater.from(context).inflate(R.layout.item_lv_yeucau, parent, false);
        return v;
    }
}
