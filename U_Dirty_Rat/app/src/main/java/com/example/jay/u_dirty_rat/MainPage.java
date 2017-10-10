package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;


public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Button
        Button logOutButton = (Button) findViewById(R.id.logout);

        //action listener and intent
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, WelcomeScreen.class));
            }
        });

        //loading in CSV file.
        InputStream inputStream = getResources().openRawResource(R.raw.rat_sightings);
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(inputStream));
        try {
            reader.readLine();
        } catch (IOException e) {
        }

        String rawReport = "";



    }

}
