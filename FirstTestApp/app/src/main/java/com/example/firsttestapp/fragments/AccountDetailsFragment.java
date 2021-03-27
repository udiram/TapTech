package com.example.firsttestapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.firsttestapp.R;
import com.example.firsttestapp.activities.AddDetailsActivity;
import com.example.firsttestapp.activities.AddMachineActivity;
import com.example.firsttestapp.activities.SeeExistingActivity;
import com.example.firsttestapp.data.PermanentStorage;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailsFragment extends Fragment {
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

    public AccountDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);

        name = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.USER_KEY);
        account = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY);
        company = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.COMPANY_KEY);
        phone = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.PHONE_KEY);
        email = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.EMAIL_KEY);
        address = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.ADDRESS_KEY);

        account_name = view.findViewById(R.id.account_name);
        account_id = view.findViewById(R.id.account_number);
        company_name = view.findViewById(R.id.company_name);
        phone_number = view.findViewById(R.id.phone_number);
        email_id = view.findViewById(R.id.email_id);
        address_id = view.findViewById(R.id.address_id);

        account_name.setText(name);
        account_id.setText(account);
        company_name.setText(company);
        phone_number.setText(phone);
        email_id.setText(email);
        address_id.setText(address);

        Button add = view.findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getActivity(), AddMachineActivity.class);
                startActivity(add);
            }
        });

        Button details= view.findViewById(R.id.addDetailsBtn);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(getActivity(), AddDetailsActivity.class);
                startActivity(add);
            }
        });

        Button see = view.findViewById(R.id.seeBtn);
        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = PermanentStorage.getInstance().retrieveString(getActivity(), PermanentStorage.MAKE_KEY);

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(),"Please Add Machine under Add machine To continue", Toast.LENGTH_LONG).show();
                } else {
                    Intent report = new Intent(getActivity(), SeeExistingActivity.class);
                    startActivity(report);
                }

            }
        });


        return view;
    }
}
