package com.example.s_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Activity_For_n.Create_Post_Company;
import com.example.s_job.Custom.Custom_lv_DangTin;
import com.example.s_job.Fragment.Company_Profile;
import com.example.s_job.Model.PostForCompany;
import com.example.s_job.db_firebase.dbFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class favoritedJobs extends AppCompatActivity {

    ListView listView;
    ImageButton back, create;
    Intent intent;
    public static ArrayList<PostForCompany> posts = new ArrayList<>();
    Custom_lv_DangTin adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorited_jobs);
        // getActionBar().setDisplayHomeAsUpEnabled(true);


        setControl();
        setEvent();
    }


    private void setEvent() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), Create_Post_Company.class);
                intent.putExtra("chitiet", false);
                startActivity(intent);
            }
        });





    }

    private void LoadData() {


        dbFireBase db = new dbFireBase();

        db.myRef.child("Post-Company").child(MainActivity1.User).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    posts.clear();

                    for (DataSnapshot key : snapshot.getChildren()) {
                        PostForCompany data = key.getValue(PostForCompany.class);
                        data.setCompany(Company_Profile.company);
                        data.setKey(key.getKey());

                        posts.add(data);
                    }
                    adapter = new Custom_lv_DangTin(favoritedJobs.this, posts);
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setControl() {
        listView = findViewById(R.id.lv_dsDangTin);
        back = findViewById(R.id.img_back);
        create = findViewById(R.id.img_Create);
        // toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        posts.clear();
        LoadData();


        super.onResume();
    }

    @Override
    protected void onStop() {
        posts.clear();
        LoadData();
        super.onStop();
    }
}