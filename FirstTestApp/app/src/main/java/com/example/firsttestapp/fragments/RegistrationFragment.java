package com.example.firsttestapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.MainActivity;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.operations.RegistrationEmailOperation;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        final EditText name = view.findViewById(R.id.name);
        final EditText number = view.findViewById(R.id.number);
        final EditText emailid = view.findViewById(R.id.email);
        final EditText company = view.findViewById(R.id.company);
        final EditText address = view.findViewById(R.id.address);
        boolean clicked = false;
        final String ischecked = "yes";
        final Button registerBtn = view.findViewById(R.id.register);
        final CheckBox checkboxvariable = (CheckBox) view.findViewById(R.id.terms);


        TextView tctext = (TextView) view.findViewById(R.id.textView2);

        tctext.setText(Html.fromHtml("I have read and agree to the " +
                "<a href='google.com'>TERMS AND CONDITIONS</a>"));
        tctext.setMovementMethod(LinkMovementMethod.getInstance());
        tctext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://foursquareoffice.com/terms-and-conditions"));
                startActivity(browserIntent);

                checkboxvariable.setText("");

            }
        });
        checkboxvariable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkboxvariable.isChecked()) {

                    registerBtn.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "agreed to terms and conditions", Toast.LENGTH_LONG).show();

                } else {

                    registerBtn.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "please agree to the terms and conditions to proceed", Toast.LENGTH_LONG).show();

                }
            }

        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEmail(emailid.getText())) {
                    Toast.makeText(getActivity(), "please enter a valid email address to continue", Toast.LENGTH_LONG).show();
                    return;
                }



                if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty() || emailid.getText().toString().isEmpty() || company.getText().toString().isEmpty() || address.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill in all fields to continue", Toast.LENGTH_LONG).show();
                } else {
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.USER_KEY, name.getText().toString());
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.PHONE_KEY, number.getText().toString());
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.EMAIL_KEY, emailid.getText().toString());
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, accountGeneration(name.getText().toString()));
//                PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, GenerateRandomString.randomString(10));
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.COMPANY_KEY, company.getText().toString());
                    PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ADDRESS_KEY, address.getText().toString());

                    Intent report = new Intent(getActivity(), MainActivity.class);
                    startActivity(report);

                    sendMail();
                }

            }
        });

        return view;
    }

    private String accountGeneration(String name) {

        Random random = new Random();
        int randomNumber = random.nextInt(1000000);

        String result = "" + randomNumber;

        return result;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    //public static class GenerateRandomString {
//
//    private static final String DATA = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    private static Random RANDOM = new Random();
//
//    private static String randomString(int len) {
//        StringBuilder sb = new StringBuilder(len);
//
//        for (int i = 0; i < len; i++) {
//            sb.append(DATA.charAt(RANDOM.nextInt(DATA.length())));
//        }
//
//        return sb.toString();
//    }
    private void sendMail() {
        try {
            RegistrationEmailOperation l = new RegistrationEmailOperation(getActivity());
            l.execute();  //sends the email in background
            Toast.makeText(getActivity(), l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}

