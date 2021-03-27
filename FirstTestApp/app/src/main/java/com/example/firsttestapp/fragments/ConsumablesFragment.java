package com.example.firsttestapp.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.OtherConsumablesActivity;
import com.example.firsttestapp.activities.PaperActivity;
import com.example.firsttestapp.activities.StaplesActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumablesFragment extends Fragment {

    public ConsumablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_consumables, container, false);


        Button otherBtn = view.findViewById(R.id.other);
        otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent other = new Intent(getActivity(), OtherConsumablesActivity.class);
                startActivity(other);
            }
        });

        Button tonerBtn = view.findViewById(R.id.toner);
        tonerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_toner);

            }
        });

        Button paperBtn = view.findViewById(R.id.paper);
        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paper = new Intent(getActivity(), PaperActivity.class);
                startActivity(paper);
            }
        });

        Button staplesBtn = view.findViewById(R.id.staples);
        staplesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent staples = new Intent(getActivity(), StaplesActivity.class);
                startActivity(staples);
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
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}


