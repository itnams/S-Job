package com.example.s_job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.s_job.Fragment.User_Home;

import org.w3c.dom.Text;

import java.util.List;

public class PostCompanyAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<PostCompany> arrayPostCompany;
public PostCompanyAdapter(Context context, int layout, List<PostCompany> postCompanyList)
{
    myContext = context;
    myLayout = layout;
    arrayPostCompany = postCompanyList;
}
    @Override
    public int getCount() {
        return arrayPostCompany.size();
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
        TextView txtTieuDe = (TextView) convertView.findViewById(R.id.txtTieuDe);
        TextView txtTinhThanh = (TextView) convertView.findViewById(R.id.txtTinhThanh);
        TextView txtNgayDang = (TextView) convertView.findViewById(R.id.txtNgayDangllllll);
        txtTieuDe.setText(arrayPostCompany.get(position).key);
        txtNgayDang.setText(arrayPostCompany.get(position).ngayDang);
        txtTinhThanh.setText(arrayPostCompany.get(position).tinhThanh);
        return convertView;
    }
}
