package com.example.firsttestapp.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.fragments.RegistrationFragment;

public class ManualRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_registration);

        String name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);

        if (name.isEmpty()) {
            Fragment regFragment = new RegistrationFragment();

            loadFragment(regFragment);
        } else {
            Intent menuActivity  = new Intent(ManualRegistrationActivity.this, MainActivity.class);

            startActivity(menuActivity);

//            Fragment menuFragment = new MenuFragment();
//
//            loadFragment(menuFragment);
        }
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
