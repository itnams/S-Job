package com.example.s_job.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.s_job.Activity_For_n.Create_Post_Company;
import com.example.s_job.AllPost;
import com.example.s_job.DetailActivity;
import com.example.s_job.PostCompany;
import com.example.s_job.PostCompanyAdapter;
import com.example.s_job.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User_Home extends Fragment {

    private Spinner sprDiaDiem;
    ListView listViewPostCompany;
    ArrayList<PostCompany> arrayListPost;
    DatabaseReference mData;
    public static String emai,key,ngayDang,tinhThanh,tieude;
    Create_Post_Company create_post_company;

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
        mData.child("All-Post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                AllPost allPost = snapshot.getValue(AllPost.class);
                arrayListPost.add(new PostCompany(allPost.emai,allPost.key,allPost.ngayDang,allPost.tieuDe,allPost.tinhThanh));
                PostCompanyAdapter adapter = new PostCompanyAdapter(
                        getContext().getApplicationContext(),R.layout.dong_post_company,arrayListPost);
                listViewPostCompany.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<String>();
        sprDiaDiem.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, create_post_company.TinhThanhs));
        sprDiaDiem.setSelection(0);
        listViewPostCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tieude = arrayListPost.get(position).tieude;
                emai = arrayListPost.get(position).emai;
                key = arrayListPost.get(position).key;
                ngayDang = arrayListPost.get(position).ngayDang;
                tinhThanh = arrayListPost.get(position).tinhThanh;
                Intent intent = new Intent(getContext().getApplicationContext(), DetailActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
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