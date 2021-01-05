package com.example.s_job.Activity_For_n;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.s_job.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Goole_map extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    public static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));
    public static String edtAddress ;
    AutoCompleteTextView mSearchText;
    ImageView mGps;
    ImageView mdone;
    PlaceAutoCompleteAdapter adapter;
    GoogleApiClient mGoogleApi;


    GoogleMap map;
    Boolean mLocationPremisss = false;
    FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goole_map);
        mSearchText = findViewById(R.id.input_search);
        mGps = findViewById(R.id.ic_gps);
        mdone = findViewById(R.id.ic_done);
        // Construct a FusedLocationProviderClient.


        getLocationPermission();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("address", edtAddress);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("address", edtAddress);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    void init() {
        mGoogleApi = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        adapter = new PlaceAutoCompleteAdapter(this, mGoogleApi, LAT_LNG_BOUNDS, null);
        mSearchText.setAdapter(adapter);
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH
                        || i == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {
                    geoLocate();

                }
                return false;
            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDeviceLocation();
            }
        });
        mdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Create_Post_Company.diaChi.setText(edtAddress);
                onBackPressed();
            }
        });
        hideSoftKeyboard();
    }

    private void geoLocate() {
        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(Goole_map.this);
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        if (addressList.size() > 0) {
            Address address = addressList.get(0);
            edtAddress = address.getAddressLine(0);
            //Toast.makeText(this, ""+address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), 15f, address.getAddressLine(0));
        }
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
//        LatLng hcm = new LatLng(10.8514325, 106.7558754);
//        map.addMarker(new MarkerOptions().position(hcm).title("TP.HCM"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(hcm));
        if (mLocationPremisss) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }

    void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPremisss) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location current = (Location) task.getResult();
                            moveCamera(new LatLng(current.getLatitude(), current.getLongitude()), 15f, "My Location");
                        }
                    }
                });
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    void moveCamera(LatLng latLng, float zoom, String title) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        if (!title.equals("My Location")) {
            MarkerOptions options = new MarkerOptions().position(latLng).title(title);
            map.addMarker(options);
        }
        hideSoftKeyboard();

    }

    void getLocationPermission() {
        String[] permision = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(Goole_map.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(Goole_map.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPremisss = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permision, 1234);
            }
        } else {
            ActivityCompat.requestPermissions(this, permision, 1234);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPremisss = false;
        switch (requestCode) {
            case 1234: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPremisss = false;
                            return;
                        }
                    }
                    mLocationPremisss = true;
                }
            }
            initMap();
        }

    }

    void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}