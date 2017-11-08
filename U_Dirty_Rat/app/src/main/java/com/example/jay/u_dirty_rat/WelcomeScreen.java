package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
public class WelcomeScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "WelcomeScreen";
    public static List<Rat> database = new Stack();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        //csv version
        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(inputStream));
        try {
            String rawreport = reader.readLine();
            int counter = 0;
            while(rawreport != null && counter < 1000) { //while there is a report,
                String[] pieces = rawreport.split(",",-1);
                try {

                    Rat report = new Rat(parseInt(pieces[0]),
                            pieces[1],
                            pieces[2],
                            parseInt(pieces[3]),
                            pieces[4],
                            pieces[5],
                            pieces[6],
                            parseDouble(pieces[7]),
                            parseDouble(pieces[8])); //create rat class object (report).
                    database.add(report); //adding object to the database(stack).
                }
                catch (NumberFormatException nfe) {

                }
                //reports.child(pieces[1]).setValue(report);
          //reports.child(pieces[1]).setValue(report);

                counter++;
                rawreport = reader.readLine();
            }
        } catch (IOException e) {
        }
        //creating button objects to use as action listener.
        Button loginButton = (Button) findViewById(R.id.LogInButton);
        Button registerButton = (Button) findViewById(R.id.RegisterButton);
        Log.d(TAG, "Created Welcome Screen");
        mAuth = FirebaseAuth.getInstance();
        //Firebase Checking if user is already logged in and track log in status
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //startActivity(new Intent(WelcomeScreen.this, MainPage.class));
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
                Log.d(TAG, "Inside mAuthListner");
            }

        };
        Log.d(TAG, "Outside mAuthListner");

        //set the listener and intent.
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this, LoginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreen.this, RegistrationActivity.class));
            }
        });

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