package com.example.s_job.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.s_job.AllPost;
import com.example.s_job.DetailActivity;
import com.example.s_job.Luutin;
import com.example.s_job.PostCompany;
import com.example.s_job.PostCompanyAdapter;
import com.example.s_job.R;
import com.example.s_job.YourFavorited;
import com.example.s_job.activity.Login;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class User_Notification extends Fragment {
ListView listviewnotication;
    DatabaseReference mData;
    DatabaseReference nData;
    PostCompanyAdapter adapter;
    User_Home user_home;
    Login login;
    ArrayList<PostCompany> arrayListPost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_user__notification, container, false);
        listviewnotication = view.findViewById(R.id.listviewnotication);
        arrayListPost = new ArrayList<PostCompany>();
        mData = FirebaseDatabase.getInstance().getReference();
        nData = FirebaseDatabase.getInstance().getReference();
        DateFormat dateFormatter = new SimpleDateFormat("d/M/yyyy");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        mData.child("All-Post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                AllPost allPost = snapshot.getValue(AllPost.class);
                nData.child("All-Post").child(allPost.key).child("Luu-tin").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Luutin luutin = snapshot.getValue(Luutin.class);
                        if (luutin.username.equals(login.userLogin))
                        {
                            if (s.equals(allPost.ngayDang))
                            {
                                arrayListPost.add(new PostCompany(allPost.emai,allPost.key,allPost.ngayDang + "[Sắp hết hạn nộp]",allPost.tieuDe,allPost.tinhThanh));
                                adapter = new PostCompanyAdapter(
                                        getContext(),R.layout.dong_post_company,arrayListPost);
                                listviewnotication.setAdapter(adapter);
                            }
                        }
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
        listviewnotication.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_home.tieude = arrayListPost.get(position).tieude;
                user_home.emai = arrayListPost.get(position).emai;
                user_home.key = arrayListPost.get(position).key;
                user_home.ngayDang = arrayListPost.get(position).ngayDang;
                user_home.tinhThanh = arrayListPost.get(position).tinhThanh;
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        return view;
    }
}