package com.example.s_job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.s_job.Fragment.User_Home;
import com.example.s_job.activity.Login;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class YourFavorited extends AppCompatActivity {
    ListView listViewYourFavorited;
    DatabaseReference mData;
    DatabaseReference nData;
    PostCompanyAdapter adapter;
    User_Home user_home;
    Login login;
    ArrayList<PostCompany> arrayListPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_favorited);
        listViewYourFavorited = findViewById(R.id.listViewYourFavorited);
        arrayListPost = new ArrayList<PostCompany>();
        mData = FirebaseDatabase.getInstance().getReference();
        nData = FirebaseDatabase.getInstance().getReference();
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
                            arrayListPost.add(new PostCompany(allPost.emai,allPost.key,allPost.ngayDang,allPost.tieuDe,allPost.tinhThanh));
                            adapter = new PostCompanyAdapter(
                                    YourFavorited.this,R.layout.dong_post_company,arrayListPost);
                            adapter.notifyDataSetChanged();
                            listViewYourFavorited.setAdapter(adapter);

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
        listViewYourFavorited.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_home.tieude = arrayListPost.get(position).tieude;
                user_home.emai = arrayListPost.get(position).emai;
                user_home.key = arrayListPost.get(position).key;
                user_home.ngayDang = arrayListPost.get(position).ngayDang;
                user_home.tinhThanh = arrayListPost.get(position).tinhThanh;
                Intent intent = new Intent(YourFavorited.this, DetailActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}