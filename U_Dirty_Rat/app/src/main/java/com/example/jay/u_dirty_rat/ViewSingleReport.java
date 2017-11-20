package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import static com.example.jay.u_dirty_rat.MainPage.selected;

public class ViewSingleReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_report);

        //create test view variables for each content.

        TextView content1 = (TextView) findViewById(R.id.uniqueContent);
        TextView content2 = (TextView) findViewById(R.id.dateContent);
        TextView content3 = (TextView) findViewById(R.id.locationTypeContent);
        TextView content4 = (TextView) findViewById(R.id.incidentZipContent);
        TextView content5 = (TextView) findViewById(R.id.incAddressContent);
        TextView content6 = (TextView) findViewById(R.id.cityContent);
        TextView content7 = (TextView) findViewById(R.id.boroughContent);
        TextView content8 = (TextView) findViewById(R.id.latitudeContent);
        TextView content9 = (TextView) findViewById(R.id.longitudeContent);

        //assign attribute of report to the text.
        
        content1.setText(selected.getUniqueKey());
        content2.setText(selected.getDate());
        content3.setText(selected.getLocationType());
        content4.setText(selected.getIncidentZip());
        content5.setText(selected.getIncidentAddress());
        content6.setText(selected.getCity());
        content7.setText(selected.getBorough());
        content8.setText(String.valueOf(selected.getLatitude()));
        content9.setText(String.valueOf(selected.getLongitude()));

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> startActivity(new Intent(ViewSingleReport.this, MainPage.class)));

    }
}
