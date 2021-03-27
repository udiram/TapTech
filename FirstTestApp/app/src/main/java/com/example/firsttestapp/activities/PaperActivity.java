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
import com.example.firsttestapp.data.PaperDataHolder;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.operations.PaperEmailOperation;
import com.google.android.material.snackbar.Snackbar;

public class PaperActivity extends AppCompatActivity {

    public String name = "";
    public String account = "";
    public String company = "";
    public String phone = "";
    public String email = "";
    public String type = "";


    EditText shippingAddress;
    EditText size;
    EditText quantity;
    EditText weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);


        shippingAddress = findViewById(R.id.address);
        size = findViewById(R.id.paperSize);
        weight = findViewById(R.id.gsm);
        quantity = findViewById(R.id.quantity);


        name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.EMAIL_KEY);

        ImageView goback = (ImageView) findViewById(R.id.goback);

        goback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            Intent goback = new Intent(PaperActivity.this, ConsumablesActivity.class);
            startActivity(goback);
            }

        });
        Button send = findViewById(R.id.paperBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shippingAddress.getText().toString().isEmpty() || size.getText().toString().isEmpty() || weight.getText().toString().isEmpty() || quantity.getText().toString().isEmpty()) {

                    Snackbar.make(v, "Please fill in all fields", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    Snackbar.make(v, "order placed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
                    Intent done = new Intent(PaperActivity.this, ConsumablesActivity.class);
                    startActivity(done);
                    holdPaperData();

                    sendMail();
                }
            }

        });
        Spinner makeSpinner = findViewById(R.id.paperTypeSpinner);
        makeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), make + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
                type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }


    private void sendMail() {
        try {
            PaperEmailOperation l = new PaperEmailOperation(this);
            l.execute();  //sends the email in background
            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    public void holdPaperData() {
        PaperDataHolder data = PaperDataHolder.getInstance();


        data.setAddress(shippingAddress.getText().toString());
        data.setPaperSize(Integer.parseInt(size.getText().toString()));
        data.setQuantity(Integer.parseInt(quantity.getText().toString()));
        data.setGsm(Integer.parseInt(weight.getText().toString()));
        data.setPaperType(type);
    }

//    protected void sendEmail() {
//
//        Log.i("Send email", "");
//        String[] TO = {"sathya6876@gmail.com"};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Paper");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "Paper quantity:" + quantity.getText() + "\n" + "Paper Type:" + type + "\n" + "Paper size:" + size.getText() + "\n" + "Paper GSM:" + weight.getText() + "\n" + "current meter read:" +  "\n"+ name + "\n" + company + "\n" + account + "\n" + phone + "\n" + email + "\n" + " ship to: " + shippingAddress.getText());
//
//        try {
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            finish();
//            Log.i("Finished sending email...", "");
//
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(PaperActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//
//        }


}

