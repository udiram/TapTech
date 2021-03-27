package com.example.firsttestapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.operations.RegistrationEmailOperation;

public class AddDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        final EditText number = findViewById(R.id.number);
        final EditText company = findViewById(R.id.company);
        final EditText address = findViewById(R.id.address);
        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);

        final Button editBtn = findViewById(R.id.editBtn);


        final CheckBox checkboxvariable = (CheckBox) findViewById(R.id.terms);
        checkboxvariable.setText("");

        TextView tctext = (TextView) findViewById(R.id.textView);
        tctext.setText(Html.fromHtml("I have read and agree to the " +  "<a href='google.com'>TERMS AND CONDITIONS</a>"));
        tctext.setMovementMethod(LinkMovementMethod.getInstance());
        tctext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquareoffice.com/terms-and-conditions"));
                startActivity(browserIntent);
            }
        });
        checkboxvariable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkboxvariable.isChecked()) {

                    editBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(AddDetailsActivity.this, "agreed to terms and conditions", Toast.LENGTH_LONG).show();

                } else {

                    editBtn.setVisibility(View.INVISIBLE);
                    Toast.makeText(AddDetailsActivity.this, "please agree to the terms and conditions to proceed", Toast.LENGTH_LONG).show();

                }
            }

        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.PHONE_KEY, number.getText().toString());
//                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.ACCOUNT_ID_KEY, accountGeneration(name.getText().toString()));
//                PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, GenerateRandomString.randomString(10));
                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.COMPANY_KEY, company.getText().toString());
                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.ADDRESS_KEY, address.getText().toString());
                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.EMAIL_KEY, email.getText().toString());
                PermanentStorage.getInstance().storeString(AddDetailsActivity.this, PermanentStorage.USER_KEY, name.getText().toString());

                Intent profile = new Intent(AddDetailsActivity.this, MainActivity.class);
                startActivity(profile);



                sendMail();

                Toast.makeText(AddDetailsActivity.this, "All changes succesfully saved", Toast.LENGTH_LONG);
            }
        });

    }

    private void sendMail() {
        try {
            RegistrationEmailOperation l = new RegistrationEmailOperation(this);
            l.execute();  //sends the email in background
            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}
