package com.example.firsttestapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;

public class AddMachineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);

        final EditText make = findViewById(R.id.make);
        final EditText serial = findViewById(R.id.serial_number);
        final EditText location = findViewById(R.id.address);
        final EditText impressions = findViewById(R.id.impressions);
        final EditText model = findViewById(R.id.model);

        Button add_acceptBtn = findViewById(R.id.add_accept);
        add_acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent report = new Intent(AddMachineActivity.this, ReportActivity.class);
                startActivity(report);
                PermanentStorage.getInstance().storeString(AddMachineActivity.this, PermanentStorage.MAKE_KEY, make.getText().toString());
                PermanentStorage.getInstance().storeString(AddMachineActivity.this, PermanentStorage.MODEL_KEY, model.getText().toString());
                PermanentStorage.getInstance().storeString(AddMachineActivity.this, PermanentStorage.SERIAL_NUMBER_KEY, serial.getText().toString());
//                PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, GenerateRandomString.randomString(10));
                PermanentStorage.getInstance().storeString(AddMachineActivity.this, PermanentStorage.LOCATION_KEY, location.getText().toString());
                PermanentStorage.getInstance().storeString(AddMachineActivity.this, PermanentStorage.IMPRESSIONS_KEY, impressions.getText().toString());

            }
        });
//        return report;
    }
}
