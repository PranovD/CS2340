package com.example.jay.u_dirty_rat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

import static com.example.jay.u_dirty_rat.MainPage.reportCounter;
import static com.example.jay.u_dirty_rat.WelcomeScreen.database;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ReportingActivity extends AppCompatActivity {

    private static final String TAG = "ReportingActivity";
    FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
    DatabaseReference dbr = fbdb.getReference();
    ValueEventListener mdbListener;

    private static Rat addThis;
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
        EditText latitude = (EditText) findViewById(R.id.latInput);
        EditText longitude = (EditText) findViewById(R.id.longInput);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        submitButton.setOnClickListener(view -> {
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
        });

        cancelButton.setOnClickListener(view -> startActivity(new Intent(ReportingActivity.this,MainPage.class)));


    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener
        ValueEventListener dbListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "ReportingActivity:onCancelled", databaseError.toException());
                // ...
            }
        };
        dbr.addValueEventListener(dbListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mdbListener = dbListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mdbListener != null) {
            dbr.removeEventListener(mdbListener);
        }
    }

}
