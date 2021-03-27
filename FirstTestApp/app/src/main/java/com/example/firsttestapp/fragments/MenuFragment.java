package com.example.firsttestapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.AboutUsActivity;
import com.example.firsttestapp.activities.ConsumablesActivity;
import com.example.firsttestapp.activities.OurClientsActivity;
import com.example.firsttestapp.activities.ProfileActivity;
import com.example.firsttestapp.activities.SubmitReportActivity;
import com.example.firsttestapp.activities.TechActivity;
import com.example.firsttestapp.data.PermanentStorage;


/**
 * A simple {@link Fragment} subclass.
 */


public class MenuFragment extends Fragment {
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    public MenuFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button reportBtn = view.findViewById(R.id.report);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.COMPANY_KEY);

                if (name.isEmpty()) {
                   Toast.makeText(getActivity(),"Please Add missing Details under Account Details To continue", Toast.LENGTH_LONG).show();
                } else {
                    Intent report = new Intent(getActivity(), SubmitReportActivity.class);
                    startActivity(report);
                }

            }
        });

        Button clientBtn = view.findViewById(R.id.clients);
        clientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent client = new Intent(getActivity(), OurClientsActivity.class);
                startActivity(client);
            }
        });
        Button aboutBtn = view.findViewById(R.id.about);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(about);
            }
        });

        Button techBtn = view.findViewById(R.id.tech);
        techBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String companyName = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.COMPANY_KEY);

                if (companyName.isEmpty()) {
                    Toast.makeText(getActivity(),"Please Add missing Details under Account Details To continue", Toast.LENGTH_LONG).show();
                } else {
                    Intent report = new Intent(getActivity(), TechActivity.class);
                    startActivity(report);
                }

            }
        });

        Button accountBtn = view.findViewById(R.id.accountbutton);
        accountBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent report = new Intent(getActivity(), ProfileActivity.class);
                startActivity(report);
            }
        });

        Button consumableBtn = view.findViewById(R.id.consumables);
        consumableBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.COMPANY_KEY);

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(),"Please Add missing Details under Account Details To continue", Toast.LENGTH_LONG).show();
                } else {
                    Intent report = new Intent(getActivity(), ConsumablesActivity.class);
                    startActivity(report);
                }

            }
        });


        Button contactBtn = view.findViewById(R.id.contact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
                sendEmail();
            }

        });



        return view;
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
            getActivity().finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }


    }
}
