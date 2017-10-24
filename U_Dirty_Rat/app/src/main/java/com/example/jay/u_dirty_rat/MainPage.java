package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class MainPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    public static Rat selected;
    public static int mostRecent;
    DatabaseReference reports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Firebase Checking if user is already logged in and track log in status
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        mAuth = FirebaseAuth.getInstance();

        //Button
        Button logOutButton = (Button) findViewById(R.id.logout);

        //action listener and intent
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainPage.this, WelcomeScreen.class));
            }
        });

        //loading in CSV file as an array list of rat class objects.

        List database = new ArrayList();
        //json version
        reports = FirebaseDatabase.getInstance().getReference();
        reports.removeValue();


        //csv version
        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(inputStream));
        try {
            String rawreport;
            while((rawreport = reader.readLine()) != null) { //while there is a report,
                String[] pieces = rawreport.split(",",-1);
                Rat report = new Rat(parseInt(pieces[0]),
                        pieces[1],
                        pieces[2],
                        parseInt(pieces[3]),
                        pieces[4],
                        pieces[5],
                        pieces[6],
                        parseDouble(pieces[7]),
                        parseDouble(pieces[8])); //create rat class object (report).
                database.add(report); //adding object to the database(arraylist).
        }
        } catch (IOException e) {
        }

        //display reports by using database and implementing list view widget.
        ListView recentList = (ListView) findViewById(R.id.recentList);
        ArrayAdapter<Rat> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, database);

        recentList.setAdapter(arrayAdapter);

        recentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = i;
                //create selected report as a static variable so that I can use this on view page.
                selected = (Rat) recentList.getItemAtPosition(position);
                startActivity(new Intent(MainPage.this, ViewSingleReport.class));
            }
        });

        Button reportButton = (Button) findViewById(R.id.reportButton);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostRecent = 0;
                for (Iterator<Rat> i = database.iterator(); i.hasNext();) {
                    Rat report = i.next();
                    if(mostRecent == 0 || mostRecent < report.getUniqueKey()) {
                        mostRecent = report.getUniqueKey();
                    }
                }
                startActivity(new Intent(MainPage.this, ReportingActivity.class));
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
