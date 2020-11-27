package com.example.s_job.Activity_For_n;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.s_job.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Goole_map extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goole_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng hcm = new LatLng(10.8514325, 106.7558754);
        map.addMarker(new MarkerOptions().position(hcm).title("TP.HCM"));
        map.moveCamera(CameraUpdateFactory.newLatLng(hcm));

    }
}