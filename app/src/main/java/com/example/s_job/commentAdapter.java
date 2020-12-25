package com.example.s_job;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class commentAdapter extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<Comment>arrayComment;
    public commentAdapter(Context context, int layout, List<Comment>commentList)
    {
        mycontext = context;
        myLayout = layout;
        arrayComment = commentList;
    }
    @Override
    public int getCount() {
        return arrayComment.size();
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
        LayoutInflater inflater =(LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout,null);
        TextView txtUserComment = convertView.findViewById(R.id.txtUserComment);
        TextView txtcomment = convertView.findViewById(R.id.txtcomment);
        txtUserComment.setText(arrayComment.get(position).user);
        txtcomment.setText(arrayComment.get(position).comment);
        return convertView;
    }
}
