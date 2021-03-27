package com.example.firsttestapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;



public class ConsumablesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumables);

        ImageView goback = (ImageView) findViewById(R.id.goback);

        goback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent goback = new Intent(ConsumablesActivity.this, MainActivity.class);
                startActivity(goback);
            }

        });
        Button otherBtn = findViewById(R.id.other);
        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other = new Intent(ConsumablesActivity.this, OtherConsumablesActivity.class);
                startActivity(other);
            }
        });

        Button tonerBtn = findViewById(R.id.toner);
        tonerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toner = new Intent(ConsumablesActivity.this, TonerActivity.class);
                startActivity(toner);
            }
        });

        Button paperBtn = findViewById(R.id.paper);
        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paper = new Intent(ConsumablesActivity.this, PaperActivity.class);
                startActivity(paper);
            }
        });

        Button staplesBtn = findViewById(R.id.staples);
        staplesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staples = new Intent(ConsumablesActivity.this, StaplesActivity.class);
                startActivity(staples);
            }
        });
        Button contactBtn = findViewById(R.id.contact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();

                sendEmail();
            }

            protected void sendEmail() {
                Log.i("Send email", "");
                String[] TO = {"sathya6876@gmail.com"};
                //String[] CC = {"693763@pdsb.net"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                //emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending email...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ConsumablesActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

}



