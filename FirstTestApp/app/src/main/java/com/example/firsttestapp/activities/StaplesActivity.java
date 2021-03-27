package com.example.firsttestapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.StapleDataHolder;
import com.example.firsttestapp.operations.StapleEmailOperation;
import com.google.android.material.snackbar.Snackbar;


public class StaplesActivity extends AppCompatActivity {
    public String make = "";
    public String model = "";
    public String name = "";
    public String account = "";
    public String company = "";
    public String phone = "";
    public String email = "";


    EditText modelNumber;
    EditText shippingAddress;
    EditText finisher;
    EditText quantity;
    EditText type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staples);


        modelNumber = findViewById(R.id.model);
        shippingAddress = findViewById(R.id.address);
        finisher = findViewById(R.id.finisher);
        type = findViewById(R.id.stapleType);
        quantity = findViewById(R.id.staplesQuantity);


        name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.EMAIL_KEY);

        ImageView goback = (ImageView) findViewById(R.id.goback);

        goback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent goback = new Intent(StaplesActivity.this, ConsumablesActivity.class);
                startActivity(goback);
            }

        });
        Button send = findViewById(R.id.staplesBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shippingAddress.getText().toString().isEmpty() || finisher.getText().toString().isEmpty() || type.getText().toString().isEmpty() || quantity.getText().toString().isEmpty()) {

                    Snackbar.make(v, "Please fill in all fields", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    Snackbar.make(v, "order placed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Intent done = new Intent(StaplesActivity.this, ReportActivity.class);

                    startActivity(done);

                    holdStapleData();

                    sendMail();
                }
            }

        });


        Spinner makeSpinner = findViewById(R.id.make_spinner);
        makeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), make + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
                make = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Spinner modelSpinner = findViewById(R.id.model_spinner);
        modelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), model + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
                model = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }


    private void sendMail() {
        try {
            StapleEmailOperation l = new StapleEmailOperation(this);
            l.execute();  //sends the email in background
            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    public void holdStapleData() {
        StapleDataHolder data = StapleDataHolder.getInstance();

        data.setModelNumber(modelNumber.getText().toString());
        data.setMake(make);
        data.setAddress(shippingAddress.getText().toString());
        data.setModelType(model);
        data.setFinisher(finisher.getText().toString());
        data.setQuantity(Integer.parseInt(quantity.getText().toString()));
        data.setStapleType(type.getText().toString());

    }

}
