package com.example.s_job.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
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
        listHotro.add(getString(R.string.moKhoa));
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
                EditText et = v.findViewById(R.id.et_dialog_mota);
                et.setVisibility(View.GONE);
                spr.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listHotro));
                spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i) {
                            case 0:
                                et.setVisibility(View.GONE);

                                break;
                            case 1:
                                et.setVisibility(View.GONE);


                                break;
                            case 2:
                                et.setVisibility(View.VISIBLE);

                                break;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                builder.setNegativeButton(getString(R.string.gui), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
                    }
                }).setPositiveButton(getString(R.string.khong), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
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