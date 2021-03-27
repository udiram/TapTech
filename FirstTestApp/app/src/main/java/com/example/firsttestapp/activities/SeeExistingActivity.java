package com.example.firsttestapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;

public class SeeExistingActivity extends AppCompatActivity {
    TextView make;
    TextView model;
    TextView serial;
    TextView location;
    TextView impressions;

    private String makeStr = "";
    private String modelStr = "";
    private String locationStr = "";
    private String serialStr = "";
    private String impressionsStr= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_existing);




        makeStr = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.MAKE_KEY);
        modelStr = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.MODEL_KEY);
        serialStr = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.SERIAL_NUMBER_KEY);
        locationStr = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.LOCATION_KEY);
        impressionsStr = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.IMPRESSIONS_KEY);

        model = findViewById(R.id.model);
        make = findViewById(R.id.make);
        serial = findViewById(R.id.serial);
        location = findViewById(R.id.location);
        impressions = findViewById(R.id.impressions);

        model.setText(modelStr);
        make.setText(makeStr);
        location.setText(locationStr);
        serial.setText(serialStr);
        impressions.setText(impressionsStr);
    }
}
