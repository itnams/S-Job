package com.example.s_job;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class YourFavorited extends AppCompatActivity {
ListView listViewYourFavorited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_favorited);
        listViewYourFavorited = findViewById(R.id.listViewYourFavorited);

    }
}