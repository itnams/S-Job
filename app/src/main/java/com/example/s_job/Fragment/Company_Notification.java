package com.example.s_job.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.s_job.Model.Company;
import com.example.s_job.R;
import com.example.s_job.db_firebase.dbFireBase;


public class Company_Notification extends Fragment {
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_company__notification, container, false);
        setControl(view);
        setEvent(view);
        return view;
    }

    private void setEvent(View view) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new dbFireBase();
            }
        });
    }

    private void setControl(View view) {
        button = view.findViewById(R.id.test);
    }
}