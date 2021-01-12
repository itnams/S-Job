package com.example.s_job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.s_job.Datacode.Account;

import java.util.List;

public class ChapNhanYeuCauDoanhNghiepAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<Account> arrayYeuCauDN;
    public ChapNhanYeuCauDoanhNghiepAdapter (Context context, int layout, List<Account> dsycList)
    {
        myContext = context;
        myLayout = layout;
        arrayYeuCauDN = dsycList;
    }
    @Override
    public int getCount() {
        return arrayYeuCauDN.size();
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
        TextView tvemaildoanhnghiep = (TextView) convertView.findViewById(R.id.tvtendoanhnghiep);
        TextView tvsdtdoanhnghiep = (TextView) convertView.findViewById(R.id.tvsodienthoai);
        tvemaildoanhnghiep.setText(arrayYeuCauDN.get(position).email);
        tvsdtdoanhnghiep.setText(arrayYeuCauDN.get(position).phone);
        return convertView;
    }
}
