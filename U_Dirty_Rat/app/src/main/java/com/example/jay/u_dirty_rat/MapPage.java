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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import static com.example.jay.u_dirty_rat.WelcomeScreen.database;



public class MapPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MapActivity";
    public static Rat selected;
    public static int reportcounter = 0;
    public static int startInt = 19690720;
    public static int endInt = 20171031;
    public static List filteredDatabase = new Stack();
    // public DatabaseReference reports;

    public static void filterDB() {
        for(int i = 0; i < database.size(); i++) {
            Rat rat = (Rat) database.get(i);
            Log.d(TAG, rat.toString());
            String date = rat.getDate();

            SimpleDateFormat formatRat = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat formatFilter = new SimpleDateFormat("yyyyMMdd");
            try {

                Date formatedDate = formatRat.parse(date);
                Date startFilter = formatFilter.parse(String.valueOf(startInt));
                Log.d(TAG,"Start Date: "+ startFilter.toString());
                Date endFilter = formatFilter.parse(String.valueOf(endInt));
                Log.d(TAG,"Date: "+ formatedDate.toString());
                Log.d(TAG,"End Date: "+ endFilter.toString());
                if (!(formatedDate.compareTo(endFilter) > 0 || formatedDate.compareTo(startFilter) < 0)) {
                    filteredDatabase.add(rat);
                }
            } catch (ParseException e) {
                Log.d(TAG,"Error parsing date: "+ e.toString());
            }

        }
        Log.d(TAG,"Database: " + String.valueOf(database.size()) + " Filtered Database: " + String.valueOf(filteredDatabase.size()));
    }

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
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = 1969;
            int month = 7;
            int day = 20;

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            startInt = year*10000000 + month*1000 + day;
            Log.d(TAG, "StartDatePicker" + String.valueOf(startInt));
            filterDB();
        }
    }

    public static class EndDatePickerFragment extends DatePickerFragment{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            endInt = year*10000000 + month*1000 + day;
            Log.d(TAG, "EndDatePicker" + String.valueOf(endInt));
            filterDB();
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


