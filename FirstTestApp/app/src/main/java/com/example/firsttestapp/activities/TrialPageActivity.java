package com.example.firsttestapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TrialPageActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9703;
    private static final String TAG = SignupLoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_page);

        mAuth = FirebaseAuth.getInstance();

        SignInButton googleSignIn = findViewById(R.id.sign_in_button);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initializeGoogleSignIn();

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);


            }
        });
        Button signBtn = findViewById(R.id.sign_up);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(TrialPageActivity.this, ManualRegistrationActivity.class);
                startActivity(signin);

            }
        });

        Button loginBtn = findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(TrialPageActivity.this, ManualRegistrationActivity.class);
                startActivity(login);
            }
        });

        Button clientBtn = findViewById(R.id.clients);
        clientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent client = new Intent(TrialPageActivity.this, OurClientsActivity.class);
                startActivity(client);
            }
        });
        Button aboutBtn = findViewById(R.id.about);
        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(TrialPageActivity.this, AboutUsActivity.class);
                startActivity(about);
            }
        });

        Button contactBtn = findViewById(R.id.contact);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(),"Confirmation Email sent Successfully",Toast.LENGTH_LONG).show();
                sendEmail();
            }

        });


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

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(TrialPageActivity.this);
            if (acct != null) {

                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();

                PermanentStorage.getInstance().storeString(TrialPageActivity.this, PermanentStorage.GOOGLE_EMAIL_KEY, personEmail.toString());
                PermanentStorage.getInstance().storeString(TrialPageActivity.this, PermanentStorage.GOOGLE_FAMILY_NAME_KEY, personFamilyName.toString());
                PermanentStorage.getInstance().storeString(TrialPageActivity.this, PermanentStorage.GOOGLE_GIVEN_NAME_KEY, personGivenName.toString());
                //PermanentStorage.getInstance().storeString(Signup_Login_page_activity.this, PermanentStorage.ACCOUNT_ID_KEY, accountGeneration(name.getText().toString()));
//                PermanentStorage.getInstance().storeString(getActivity(), PermanentStorage.ACCOUNT_ID_KEY, GenerateRandomString.randomString(10));
                PermanentStorage.getInstance().storeString(TrialPageActivity.this, PermanentStorage.GOOGLE_ID_KEY, personId.toString());
                PermanentStorage.getInstance().storeString(TrialPageActivity.this, PermanentStorage.GOOGLE_NAME_KEY, personName.toString());
            }
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show();

            Intent start = new Intent(TrialPageActivity.this, ReportActivity.class);
            startActivity(start);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Sign in failed", Toast.LENGTH_LONG).show();
        }
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
            TrialPageActivity.this.finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(TrialPageActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
