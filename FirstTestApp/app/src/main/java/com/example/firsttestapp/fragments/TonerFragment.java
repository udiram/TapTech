package com.example.firsttestapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.ConsumablesActivity;
import com.example.firsttestapp.activities.ReportActivity;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.TonerDataHolder;
import com.example.firsttestapp.operations.TonerEmailOperation;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TonerFragment extends Fragment {

    public String make = "";
    public String model = "";
    public String name = "";
    public String account = "";
    public String company = "";
    public String phone = "";
    public String email = "";


    EditText modelNumber;
    EditText serialNumber;
    EditText shippingAddress;
    EditText ctoner;
    EditText mtoner;
    EditText ytoner;
    EditText ktoner;
    EditText wastetoner;
    EditText meterRead;

    public TonerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toner, container, false);
        modelNumber = view.findViewById(R.id.model);
        serialNumber = view.findViewById(R.id.serial);
        shippingAddress = view.findViewById(R.id.address);
        ctoner = view.findViewById(R.id.cyan);
        mtoner = view.findViewById(R.id.magenta);
        ytoner = view.findViewById(R.id.yellow);
        ktoner = view.findViewById(R.id.black);
        wastetoner = view.findViewById(R.id.waste);
        meterRead = view.findViewById(R.id.meter);


        name = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.EMAIL_KEY);
        ImageView goback = (ImageView) view.findViewById(R.id.goback);

        goback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent goback = new Intent(getActivity(), ConsumablesActivity.class);
                startActivity(goback);
            }

        });
        Button send = view.findViewById(R.id.tonerBtn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.isEmpty() || serialNumber.getText().toString().isEmpty() || modelNumber.getText().toString().isEmpty() || shippingAddress.getText().toString().isEmpty() || meterRead.getText().toString().isEmpty()) {

                    Snackbar.make(v, "Please fill in all fields", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {

                    Intent done = new Intent(getActivity(), ReportActivity.class);

                    startActivity(done);

                    holdTonerData();

                    sendMail();

                }
            }

        });
        Spinner makeSpinner = view.findViewById(R.id.make_spinner);
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

        Spinner modelSpinner = view.findViewById(R.id.model_spinner);
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

        return view;
    }
        private void sendMail () {
            try {
                TonerEmailOperation l = new TonerEmailOperation(getActivity());
                l.execute();  //sends the email in background
                Toast.makeText(getActivity(), l.get(), Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
        }

        public void holdTonerData () {
            TonerDataHolder data = TonerDataHolder.getInstance();

            data.setModelNumber(modelNumber.getText().toString());
            data.setMake(make);
            data.setAddress(shippingAddress.getText().toString());
            data.setMeterRead(Integer.parseInt(meterRead.getText().toString()));
            data.setSerialNumber(serialNumber.getText().toString());
            data.setModelType(model);
            data.setWaste(Integer.parseInt(wastetoner.getText().toString()));
            data.setCyan(Integer.parseInt(ctoner.getText().toString()));
            data.setMagenta(Integer.parseInt(mtoner.getText().toString()));
            data.setYellow(Integer.parseInt(ytoner.getText().toString()));
            data.setBlack(Integer.parseInt(ktoner.getText().toString()));



        }

    }

