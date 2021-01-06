package com.example.s_job.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.s_job.R;

import java.util.ArrayList;


public class Company_Notification extends Fragment {
    ImageView imgLaguage, imgBtn_hotro;
    Switch sw;
    ArrayList<String> listHotro = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_company__notification, container, false);
        setControl(view);
        setEvent(view);
        return view;
    }

    private void setEvent(View view) {
        listHotro.add(getString(R.string.hoTro));
        listHotro.add(getString(R.string.quenMatKhau));
        listHotro.add(getString(R.string.khac));
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imgLaguage.setImageResource(R.drawable.icon_en);
                } else {
                    imgLaguage.setImageResource(R.drawable.icon_vn);
                }
            }
        });
        imgBtn_hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.hoTro));
                View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_in_dialog, null);
                Spinner spr = v.findViewById(R.id.spr_hotro);
                spr.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listHotro));
                spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
                                Toast.makeText(getActivity(), "dau", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(getActivity(), "giua", Toast.LENGTH_SHORT).show();

                                break;
                            case 2:
                                Toast.makeText(getActivity(), "cuoi cung", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                builder.setView(v);

                builder.create().show();
            }
        });
    }

    private void setControl(View view) {
        imgLaguage = view.findViewById(R.id.imgV_language);
        imgBtn_hotro = view.findViewById(R.id.imgBtn_hotro);
        sw = view.findViewById(R.id.sw_changeLanguage);
    }
}