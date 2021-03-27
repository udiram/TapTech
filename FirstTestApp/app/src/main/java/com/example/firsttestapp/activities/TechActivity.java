package com.example.firsttestapp.activities;

import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class TechActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tech);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setupMap();
    }


    private void setupMap() {
        googleMap.setOnMarkerClickListener(this);

        LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        displayCurrentLocationOnMap(location);
                        addMarker(location);
                    }
                });

    }

    private void displayCurrentLocationOnMap(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 12.6f);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("You are Here"));
        googleMap.moveCamera(cameraUpdate);

    }

    private void addMarker(Location tech) {
        LatLng latLng = new LatLng(43.597060,-79.641270);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Mississauga Tech: id - 56772"));

        LatLng latLng2 = new LatLng(27.723920,-82.342200);
        googleMap.addMarker(new MarkerOptions().position(latLng2).title("bramalea tech: id - 86851"));

        LatLng latLng3 = new LatLng(43.665539,-79.738579);
        googleMap.addMarker(new MarkerOptions().position(latLng3).title("brampton tech - 45685"));

        LatLng latLng4 = new LatLng(43.381460,-79.764680);
        googleMap.addMarker(new MarkerOptions().position(latLng4).title("old office - 88525"));



    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTitle().equals("Mississaugua Tech")) {

//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquareoffice.com"));
//            startActivity(browserIntent);

        }

        if(marker.getTitle().equals("bramalea tech")){


        }

        if(marker.getTitle().equals("brampton tech")){


        }

        if(marker.getTitle().equals("old office")){


        }
        return false;


    }
}
