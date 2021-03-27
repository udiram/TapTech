package com.example.firsttestapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firsttestapp.R;
import com.example.firsttestapp.data.PermanentStorage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fabrep = findViewById(R.id.fabrep);

        fabrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyName = PermanentStorage.getInstance().retrieveString(MainActivity.this, PermanentStorage.COMPANY_KEY);

                if (companyName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Add missing Details in Account Details To continue", Toast.LENGTH_LONG).show();
                }else{

                    Snackbar.make(view, "Placing report", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Intent report = new Intent(MainActivity.this, SubmitReportActivity.class);
                    startActivity(report);
                }


            }
        });
        FloatingActionButton fabcon = findViewById(R.id.fabcon);
        fabcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyName = PermanentStorage.getInstance().retrieveString(MainActivity.this, PermanentStorage.COMPANY_KEY);

                if (companyName.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please Add missing Details in Account Details To continue", Toast.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "Placing order", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Intent consumables = new Intent(MainActivity.this, ConsumablesActivity.class);
                    startActivity(consumables);
                }


            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.account_details, R.id.request_consumables, R.id.findtech, R.id.about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
