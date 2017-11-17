package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import static com.example.jay.u_dirty_rat.MainPage.reportCounter;
import static com.example.jay.u_dirty_rat.WelcomeScreen.database;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ReportingActivity extends AppCompatActivity {

    static Rat addThis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);

        // creating edit texts for each attribute.
        EditText date = (EditText) findViewById(R.id.dateInput);
        EditText locationType = (EditText) findViewById(R.id.locTypeInput);
        EditText incidentZip = (EditText) findViewById(R.id.inciZipInput);
        EditText incidentAddress = (EditText) findViewById(R.id.inciAddrInput);
        EditText city = (EditText) findViewById(R.id.cityInput);
        EditText borough = (EditText) findViewById(R.id.boroInput);
        EditText latitude = (EditText) findViewById(R.id.laditInput);
        EditText longitude = (EditText) findViewById(R.id.longitInput);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addThis = new Rat(
                        reportCounter,
                        date.getText().toString(),
                        locationType.getText().toString(),
                        parseInt(incidentZip.getText().toString()),
                        incidentAddress.getText().toString(),
                        city.getText().toString(),
                        borough.getText().toString(),
                        parseDouble(latitude.getText().toString()),
                        parseDouble(longitude.getText().toString())
                );
                database.add(1,addThis);
                reportCounter++;
                startActivity(new Intent(ReportingActivity.this, MainPage.class));
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportingActivity.this,MainPage.class));
            }
        });


    }
}
