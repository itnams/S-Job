package com.example.s_job.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.s_job.PostCompany;
import com.example.s_job.PostCompanyAdapter;
import com.example.s_job.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User_Home extends Fragment {

    private Spinner sprDiaDiem;
    ListView listViewPostCompany;
    ArrayList<PostCompany> arrayListPost;
    DatabaseReference mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__home, container, false);
        setHasOptionsMenu(true);

        init(view);
        return view;
    }

    private void init(View view) {
        sprDiaDiem = view.findViewById(R.id.sprDiaDiem);
        listViewPostCompany = view.findViewById(R.id.listPostCompany);
        arrayListPost = new ArrayList<PostCompany>();
        mData = FirebaseDatabase.getInstance().getReference();
        arrayListPost.add(new PostCompany("a","b","c","d"));
        arrayListPost.add(new PostCompany("s","s","s","s"));
        arrayListPost.add(new PostCompany("a","b","c","d"));

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("All");
        arrayList.add("TP.HCM");
        arrayList.add("Hà Nội");
        arrayList.add("Đà Nẵng");
        ArrayAdapter arrayAdapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, arrayList);
        sprDiaDiem.setAdapter(arrayAdapter);
        PostCompanyAdapter adapter = new PostCompanyAdapter(
                getContext().getApplicationContext(),R.layout.dong_post_company,arrayListPost);
        listViewPostCompany.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_back, menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                getFragmentManager().popBackStack();
                break;
        }
        return true;
    }
}