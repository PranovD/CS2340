package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.jay.u_dirty_rat.MainPage.selected;

public class ViewSingleReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_report);

        TextView content1 = (TextView) findViewById(R.id.uniqueContent);
        TextView content2 = (TextView) findViewById(R.id.dateContent);
        TextView content3 = (TextView) findViewById(R.id.locationTypeContent);
        TextView content4 = (TextView) findViewById(R.id.incidentZipContent);
        TextView content5 = (TextView) findViewById(R.id.incAddrContent);
        TextView content6 = (TextView) findViewById(R.id.cityContent);
        TextView content7 = (TextView) findViewById(R.id.boroContent);
        TextView content8 = (TextView) findViewById(R.id.latitudeContent);
        TextView content9 = (TextView) findViewById(R.id.longitudeContent);

        content1.setText(selected.getUniqueKey());
        content2.setText(selected.getDate());
        content3.setText(selected.getLocationType());
        content4.setText(selected.getIncidentZip());
        content5.setText(selected.getIncidentAddress());
        content6.setText(selected.getCity());
        content7.setText(selected.getBorough());
        content8.setText(selected.getLatitude());
        content9.setText(selected.getLongitude());

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewSingleReport.this, MainPage.class));
            }
        });

    }
}
