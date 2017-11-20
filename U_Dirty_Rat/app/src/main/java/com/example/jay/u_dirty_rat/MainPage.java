package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static com.example.jay.u_dirty_rat.WelcomeScreen.database;

/**
 * The main page of the app.
 * It show all the functions that are available.
 */
public class MainPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    public static Rat selected;
    public static int reportCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Fire base Checking if user is already logged in and track log in status
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
            // ...
        };
        mAuth = FirebaseAuth.getInstance();

        //Button
        Button logOutButton = (Button) findViewById(R.id.logout);

        //action listener and intent
        logOutButton.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(MainPage.this, WelcomeScreen.class));
        });


        //display reports by using database and implementing list view widget.
        ListView recentList = (ListView) findViewById(R.id.recentList);
        ArrayAdapter<Rat> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, database);

        recentList.setAdapter(arrayAdapter);

        recentList.setOnItemClickListener((adapterView, view, i, l) -> {
            //create selected report as a static variable so that I can use this on view page.
            selected = (Rat) recentList.getItemAtPosition(i);
            startActivity(new Intent(MainPage.this, ViewSingleReport.class));
        });

        Button reportButton = (Button) findViewById(R.id.reportButton);

        Button mapButton = (Button) findViewById(R.id.mapButton);

        Button graphButton = (Button) findViewById(R.id.graphButton);

        reportButton.setOnClickListener(view -> startActivity(new Intent(MainPage.this, ReportingActivity.class)));

        mapButton.setOnClickListener(view -> startActivity(new Intent(MainPage.this, MapPage.class)));

        graphButton.setOnClickListener(view -> startActivity(new Intent(MainPage.this, GraphPage.class)));


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
