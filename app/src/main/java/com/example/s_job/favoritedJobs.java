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

import java.util.ArrayList;

public class favoritedJobs extends AppCompatActivity {

    ListView listView;
    ImageButton back, create;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorited_jobs);
        // getActionBar().setDisplayHomeAsUpEnabled(true);


        setControl();
        setEvent();
    }

    private void setEvent() {


        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        listView.setAdapter(new Custom_lv_DangTin(this, objects));
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


}