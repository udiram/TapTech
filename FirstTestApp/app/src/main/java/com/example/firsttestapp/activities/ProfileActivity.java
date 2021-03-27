package com.example.firsttestapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;

public class ProfileActivity extends AppCompatActivity {

    TextView account_name;
    TextView account_id;
    TextView company_name;
    TextView phone_number;
    TextView email_id;
    TextView address_id;

    private String name = "";
    private String account = "";
    private String company = "";
    private String phone = "";
    private String email = "";
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.EMAIL_KEY);
        address = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.ADDRESS_KEY);

        account_name = findViewById(R.id.account_name);
        account_id = findViewById(R.id.account_number);
        company_name = findViewById(R.id.company_name);
        phone_number = findViewById(R.id.phone_number);
        email_id = findViewById(R.id.email_id);
        address_id = findViewById(R.id.address_id);

        account_name.setText(name);
        account_id.setText(account);
        company_name.setText(company);
        phone_number.setText(phone);
        email_id.setText(email);
        address_id.setText(address);

        Button add = findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent add = new Intent(ProfileActivity.this, AddMachineActivity.class);
            startActivity(add);
            }
        });

        Button details= findViewById(R.id.addDetailsBtn);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(ProfileActivity.this, AddDetailsActivity.class);
                startActivity(add);
            }
        });

        Button see = findViewById(R.id.seeBtn);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = PermanentStorage.getInstance().retrieveString(ProfileActivity.this, PermanentStorage.MAKE_KEY);

                if (name.isEmpty()) {
                    Toast.makeText(ProfileActivity.this,"Please Add Machine under Add machine To continue", Toast.LENGTH_LONG).show();
                } else {
                    Intent report = new Intent(ProfileActivity.this, SeeExistingActivity.class);
                    startActivity(report);
                }

            }
        });

    }
}
