package com.example.s_job;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.s_job.Activity_For_n.Create_Post_Company;
import com.example.s_job.Custom.Custom_lv_DangTin;
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
    ArrayList<PostForCompany> posts = new ArrayList<>();
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

//phat


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
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout ln = view.findViewById(R.id.ln_selected_item);
                Toast.makeText(favoritedJobs.this, "click", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PostForCompany key = posts.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(favoritedJobs.this);
                builder.setTitle("Thông Báo!!")
                        .setMessage("Bạn Chắc Chắn Có Muốn Xoá " + key.getTieuDe() + " ?")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                new dbFireBase().removePost(key);
                            }
                        })
                        .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void LoadData() {


        dbFireBase db = new dbFireBase();

        db.myRef.child("Post-Company").child(MainActivity1.User).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot key : snapshot.getChildren()) {
                        PostForCompany data = key.getValue(PostForCompany.class);
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