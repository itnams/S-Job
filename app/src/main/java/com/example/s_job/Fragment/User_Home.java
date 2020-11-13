package com.example.s_job.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.s_job.R;

import java.util.ArrayList;

public class User_Home extends Fragment {

    private Spinner sprDiaDiem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__home, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        sprDiaDiem = view.findViewById(R.id.sprDiaDiem);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("All");
        arrayList.add("TP.HCM");
        arrayList.add("Hà Nội");
        arrayList.add("Đà Nẵng");
        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(),R.layout.support_simple_spinner_dropdown_item,arrayList);
        sprDiaDiem.setAdapter(arrayAdapter);
    }


}