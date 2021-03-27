package com.example.firsttestapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;

public class PasswordActivity extends AppCompatActivity {
    public String correctPassword = "Udi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        Button login = findViewById(R.id.enter);
        final EditText enter = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = enter.getText().toString();
                checkPassword(result);


            }
        });
//        String name = PermanentStorage.getInstance().retrieveString(this, PermanentStorage.USER_KEY);
//
//        if (name.isEmpty()) {
//            Fragment regFragment = new RegistrationFragment();
//
//            loadFragment(regFragment);
//        } else {
//            Fragment regFragment = new MenuFragment();
//
//            loadFragment(regFragment);
//
//        }

    }
//    private void loadFragment(Fragment fragment) {
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit(); // save the changes
//    }
    private void doToast(String toastMessage) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(toastMessage);


        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 0, 0);
        toast.setView(layout);
        toast.show();
//        Toast toast = Toast.makeText(this, toastmessage, Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 0); //this is for changing where the toast pops up
//        toast.show();
    }
    private void checkPassword(String password) {
        if (password.equals(correctPassword)){
            doToast("Correct!");
            moveForward();
        }
        else {
            doToast("Try Again!");
        }
    }
    private void moveForward(){
        Intent moveForward = new Intent(this, ReportActivity.class);
        startActivity(moveForward);

    }
}
