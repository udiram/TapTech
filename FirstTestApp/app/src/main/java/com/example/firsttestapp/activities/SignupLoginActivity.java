package com.example.firsttestapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.operations.RegistrationEmailOperation;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class SignupLoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9703;
    private static final String TAG = SignupLoginActivity.class.getName();
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__login_page_activity);

// Initialize Firebase Auth

        int images[] = {R.mipmap.networkguy, R.mipmap.net2, R.mipmap.security, R.mipmap.sec1, R.mipmap.repairguy, R.mipmap.tech2, R.mipmap.customerservice};
        viewFlipper = findViewById(R.id.v_flip);

        for (int i = 0; i < images.length; i++) {

            flipper(images[i]);
        }
//        for (int image: images){
//
//            flipper(image);
//        }

        mAuth = FirebaseAuth.getInstance();

        Button signBtn = findViewById(R.id.sign_up);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signin = new Intent(SignupLoginActivity.this, ManualRegistrationActivity.class);
                startActivity(signin);


            }
        });

        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(SignupLoginActivity.this, ManualRegistrationActivity.class);
                startActivity(login);
            }
        });

        Button skipBtn = findViewById(R.id.skip);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skip = new Intent(SignupLoginActivity.this, TrialPageActivity.class);
                startActivity(skip);
            }
        });

        SignInButton googleSignIn = findViewById(R.id.sign_in_button);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initializeGoogleSignIn();

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);



            }
        });

    }

    private String accountGeneration(String name) {

        Random random = new Random();
        int randomNumber = random.nextInt(1000000);

        String result = "" + randomNumber;

        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void initializeGoogleSignIn() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(SignupLoginActivity.this);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                PermanentStorage.getInstance().storeString(SignupLoginActivity.this, PermanentStorage.EMAIL_KEY, personEmail.toString());
                PermanentStorage.getInstance().storeString(SignupLoginActivity.this, PermanentStorage.USER_KEY, personFamilyName.toString());
                PermanentStorage.getInstance().storeString(SignupLoginActivity.this, PermanentStorage.USER_KEY, personGivenName.toString());
                //PermanentStorage.getInstance().storeString(Signup_Login_page_activity.this, PermanentStorage.ACCOUNT_ID_KEY, accountGeneration(name.getText().toString()));
//                PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, GenerateRandomString.randomString(10));
                PermanentStorage.getInstance().storeString(SignupLoginActivity.this, PermanentStorage.ACCOUNT_ID_KEY, personId.toString());
                PermanentStorage.getInstance().storeString(SignupLoginActivity.this, PermanentStorage.USER_KEY, personName.toString());
            }
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show();

            Intent start = new Intent(SignupLoginActivity.this, MainActivity.class);
            startActivity(start);

            sendMail();


        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show();
        }
    }

    public void flipper(int image) {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out);

    }

    private void sendMail() {
        try {
            RegistrationEmailOperation l = new RegistrationEmailOperation(SignupLoginActivity.this);
            l.execute();  //sends the email in background
            Toast.makeText(SignupLoginActivity.this, l.get(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}

