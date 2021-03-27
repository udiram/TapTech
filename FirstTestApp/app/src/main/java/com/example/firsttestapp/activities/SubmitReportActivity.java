package com.example.firsttestapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.callback.OperationResultCallback;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.ReportData;
import com.example.firsttestapp.data.ReportDataHolder;
import com.example.firsttestapp.operations.PersistentReportsProvider;
import com.example.firsttestapp.operations.ServiceEmailOperation;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SubmitReportActivity extends AppCompatActivity {
    public String make = "";
    public String modelType = "";
    public String requestType = "";
    public String name = "";
    public String account = "";
    public String company = "";
    public String phone = "";
    public String email = "";
    public String timestamp = "timestamp";
    public String address = "";
    public String descriptionText = "";
    public String impressions = "";
    public String serial = "";
    public String modelNumberText = "";


    EditText modelNumber;
    EditText description;
    EditText serialNumber;
    EditText shippingAddress;
    EditText meterRead;
    Map<String, String> emailMap = new HashMap<>();

    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        populateEmailMap();

        loading = (ProgressBar) findViewById(R.id.loading);

        modelNumber = findViewById(R.id.model);
        description = findViewById(R.id.description);
        serialNumber = findViewById(R.id.serial);
        shippingAddress = findViewById(R.id.address);
        meterRead = findViewById(R.id.meter);

        name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.EMAIL_KEY);


//        CheckBox checkboxvariable = (CheckBox) findViewById(R.id.accept);
        TextView tctext = (TextView) findViewById(R.id.agreeText);
//
//        checkboxvariable.setText("");
        tctext.setText(Html.fromHtml("By clicking Request Service, you agree to the " +
                "<a href='google.com'>TERMS AND CONDITIONS OF SERVICE </a>"));
        tctext.setMovementMethod(LinkMovementMethod.getInstance());
        tctext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquareoffice.com/terms-and-conditions"));
                startActivity(browserIntent);
            }
        });

//        checkboxvariable.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//            }
//
//        });
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
//                sendEmail();

//                Intent done = new Intent(SubmitReportActivity.this, ReportActivity.class);
//                if (modelNumber.getText().toString().isEmpty() || meterRead.getText().toString().isEmpty() || serialNumber.getText().toString().isEmpty()
//                        || shippingAddress.getText().toString().isEmpty()||requestType.isEmpty()||make.isEmpty()||modelType.isEmpty()) {
//
//                    Snackbar.make(v, "Please enter all fields", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

                {
                    Intent done = new Intent(SubmitReportActivity.this, MainActivity.class);
                    if (make.equals("Make") || modelType.equals("Model") || requestType.equals("Request")
                            || modelNumber.toString().isEmpty()||serialNumber.toString().isEmpty()||
                            shippingAddress.toString().isEmpty()||meterRead.toString().isEmpty()) {


                        Snackbar.make(v, "Please Review and enter all required fields", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {

                            startActivity(done);

                            holdReportData();

                            PersistentReportsProvider.storeReport(SubmitReportActivity.this, ReportDataHolder.getInstance().getReportData());
                            loading.setVisibility(View.VISIBLE);
                            sendMail();
                        }
                    }
                }


        });

        Button consumable = findViewById(R.id.consumables);
        consumable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
                Intent consumable = new Intent(SubmitReportActivity.this, ConsumablesActivity.class);

                startActivity(consumable);
            }

        });


        final Spinner makeSpinner = findViewById(R.id.make_spinner);
        makeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), make + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();

                if (makeSpinner.getSelectedItem() == "Make") {

                } else {
                    make = parent.getItemAtPosition(position).toString();

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        Spinner problemSpinner = findViewById(R.id.problem_spinner);
        problemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(parent.getContext(), make + parent.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();

                requestType = parent.getItemAtPosition(position).toString();

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

                modelType = parent.getItemAtPosition(position).toString();

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

    private void populateEmailMap() {
        emailMap.put("Ricoh", "ricoh@gmail.com");
        emailMap.put("Xerox", "Xerox@gmail.com");
        emailMap.put("Konica minolta", "KM@gmail.com");
        emailMap.put("Oki", "oki@gmail.com");
        emailMap.put("Canon", "Canon@gmail.com");
        emailMap.put("Toshiba", "Toshiba@gmail.com");
        emailMap.put("hp", "HP@gmail.com");
        emailMap.put("sharp", "sharp@gmail.com");
        emailMap.put("Lexmark", "lexmark@gmail.com");
        emailMap.put("Xanté", "xanté@gmail.com");
        emailMap.put("kyocera", "kyocera@gmail.com");
        emailMap.put("others", "others@gmail.com");
        emailMap.put("Make", "general@gmail.com");

    }

    private OperationResultCallback callbackImp = new OperationResultCallback() {
        @Override
        public void onOperationSuccess() {
            loading.setVisibility(View.GONE);
        }

        @Override
        public void onOperationFailure() {
            loading.setVisibility(View.GONE);

        }
    };

    private void sendMail() {
        try {
            ServiceEmailOperation l = new ServiceEmailOperation(this, callbackImp);
            l.execute();  //sends the email in background
            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    public void holdReportData() {
        ReportData data = ReportDataHolder.getInstance().getReportData();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String timestamp = simpleDateFormat.format(new Date());

        data.setModelNumber(modelNumber.getText().toString());
        data.setMake(make);
        data.setAddress(shippingAddress.getText().toString());
        data.setDescription(description.getText().toString());
        data.setMeterRead(meterRead.getText().toString());
        data.setSerialNumber(serialNumber.getText().toString());
        data.setModelType(modelType);
        data.setRequestType(requestType);
        data.setTimestamp(timestamp);


    }


}

