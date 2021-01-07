package com.example.s_job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResetPassAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<ResetPass> arrayResetPass;
    public ResetPassAdapter (Context context, int layout, List<ResetPass> resetPassList)
    {
        myContext = context;
        myLayout = layout;
        arrayResetPass = resetPassList;
    }
    @Override
    public int getCount() {
        return arrayResetPass.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);
        TextView tvemailrs = (TextView) convertView.findViewById(R.id.tvEmailrspass);
        TextView tvnewpass = (TextView) convertView.findViewById(R.id.tvmatkhaumuongdoi);
        tvemailrs.setText(arrayResetPass.get(position).emailrs);
        tvnewpass.setText(arrayResetPass.get(position).newpass);
        return convertView;
    }
}
