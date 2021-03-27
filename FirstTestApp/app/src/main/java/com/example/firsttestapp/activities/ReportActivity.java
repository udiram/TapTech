package com.example.firsttestapp.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.fragments.MenuFragment;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


//     Button reportBtn = findViewById(R.id.report);
//     reportBtn.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Intent report = new Intent(ReportActivity.this, SubmitReportActivity.class);
//             startActivity(report);
//         }
//     });
//        Button pastBtn = findViewById(R.id.past);
//        pastBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent past = new Intent(ReportActivity.this, PastReportActivity.class);
//                startActivity(past);
//            }
//        });
//
//        Button techBtn = findViewById(R.id.tech);
//        techBtn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent report = new Intent(ReportActivity.this, TechActivity.class);
//                startActivity(report);
//            }
//        });
//
//        Button contactBtn = findViewById(R.id.contact);
//        contactBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
//                sendEmail();
//            }
//
//        });

        String name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);

//        if (name.isEmpty()) {
//            Fragment regFragment = new RegistrationFragment();
//
//            loadFragment(regFragment);
//        } else {
//            Fragment regFragment = new MenuFragment();
//
//            loadFragment(regFragment);
//        }
        Fragment regFragment = new MenuFragment();
        Toast.makeText(this, "Logged in successfully", Toast.LENGTH_LONG).show();
        loadFragment(regFragment);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

//    protected void sendEmail() {
//        Log.i("Send email", "");
//        String[] TO = {"sathya6876@gmail.com"};
//        //String[] CC = {"693763@pdsb.net"};
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
//
//        try {
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            finish();
//            Log.i("Finished sending email...", "");
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(ReportActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
}
