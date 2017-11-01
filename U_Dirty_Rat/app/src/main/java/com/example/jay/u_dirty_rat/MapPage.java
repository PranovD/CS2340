package com.example.jay.u_dirty_rat;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Calendar;

import static com.example.jay.u_dirty_rat.WelcomeScreen.database;



public class MapPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MapActivity";
    public static Rat selected;
    public static int reportcounter = 0;
    // public DatabaseReference reports;

    //Date Picker
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            Log.d(TAG, "DatePicker" + String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
        }


    }

    public static class StartDatePickerFragment extends DatePickerFragment{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            Log.d(TAG, "StartDatePicker" + String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
        }
    }

    public static class EndDatePickerFragment extends DatePickerFragment{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            Log.d(TAG, "EndDatePicker" + String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
        }
    }

    public void showDatePickerDialogStart(View v) {
        FragmentManager fm = this.getFragmentManager();
        DialogFragment newFragment = new StartDatePickerFragment();
        newFragment.show(fm, "startDatePicker");
    }

    public void showDatePickerDialogEnd(View v) {
        FragmentManager fm = this.getFragmentManager();
        DialogFragment newFragment = new EndDatePickerFragment();
        newFragment.show(fm, "endDatePicker");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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



        //loading in CSV file as an array list of rat class objects.
        //json version
        // reports = FirebaseDatabase.getInstance().getReference();


//        //display reports by using database and implementing list view widget.
//        ListView recentList = (ListView) findViewById(R.id.recentList);
//        ArrayAdapter<Rat> arrayAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, database);
//
//        recentList.setAdapter(arrayAdapter);
//
//        recentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                int position = i;
//                //create selected report as a static variable so that I can use this on view page.
//                selected = (Rat) recentList.getItemAtPosition(position);
//                startActivity(new Intent(MapPage.this, ViewSingleReport.class));
//            }
//        });



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


