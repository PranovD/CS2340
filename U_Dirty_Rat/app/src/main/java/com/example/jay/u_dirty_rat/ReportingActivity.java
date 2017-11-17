package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.jay.u_dirty_rat.MainPage.reportcounter;
import static com.example.jay.u_dirty_rat.WelcomeScreen.database;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ReportingActivity extends AppCompatActivity {

    static Rat addThis;
    private Spinner boroughSpinner;
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
        boroughSpinner = (Spinner) findViewById(R.id.spinner);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);


        /*
          Set up the adapter to display the allowable majors in the spinner
         */
//        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Rat.Borough.values());
//        ArrayAdapter<Rat.Borough> adapter = new ArrayAdapter<Rat.Borough>(this, android.R.layout.simple_list_item_1, Rat.Borough.values());
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(new ArrayAdapter<Rat.Borough>(this, android.R.layout.simple_list_item_1));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addThis = new Rat(
                        reportcounter,
                        new Date(),
                        locationType.getText().toString(),
                        parseInt(incidentZip.getText().toString()),
                        incidentAddress.getText().toString(),
                        city.getText().toString(),
                        (Rat.Borough) boroughSpinner.getSelectedItem(),
                        parseDouble(latitude.getText().toString()),
                        parseDouble(longitude.getText().toString())
                );
                database.add(1,addThis);
                reportcounter++;
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
