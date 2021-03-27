package com.example.firsttestapp.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.firsttestapp.R;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap googleMap;
    private MapView mapView;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);//when you already implement OnMapReadyCallback in your fragment

        checkLocationPermission();
        return view;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTitle().equals("Mississaugua Tech")) {

//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquareoffice.com"));
//            startActivity(browserIntent);

        }

        if (marker.getTitle().equals("bramalea tech")) {


        }

        if (marker.getTitle().equals("brampton tech")) {


        }

        if (marker.getTitle().equals("old office")) {


        }
        return false;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setupMap();
    }

    private void setupMap() {
        googleMap.setOnMarkerClickListener(this);

        LocationServices.getFusedLocationProviderClient(getActivity()).getLastLocation().addOnSuccessListener(
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
        LatLng latLng = new LatLng(43.597060, -79.641270);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("Mississauga Tech: id - 56772"));

        LatLng latLng2 = new LatLng(27.723920, -82.342200);
        googleMap.addMarker(new MarkerOptions().position(latLng2).title("bramalea tech: id - 86851"));

        LatLng latLng3 = new LatLng(43.665539, -79.738579);
        googleMap.addMarker(new MarkerOptions().position(latLng3).title("brampton tech - 45685"));

        LatLng latLng4 = new LatLng(43.381460, -79.764680);
        googleMap.addMarker(new MarkerOptions().position(latLng4).title("old office - 88525"));


    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // request the permission
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1000);
        } else {
            Toast.makeText(getActivity(), "Please enable permissions in settings to proceed", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted.
                    setupMap();
                } else {
                    // permission denied.
                    // tell the user the action is cancelled
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                    alertDialog.setMessage("please enable location permissions to continue");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                return;
            }

        }
    }
}
