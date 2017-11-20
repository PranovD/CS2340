package com.example.jay.u_dirty_rat;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.app.DialogFragment;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import static com.example.jay.u_dirty_rat.WelcomeScreen.database;



public class MapPage extends AppCompatActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static GoogleMap map;
    private static final String TAG = "MapActivity";
    public static Rat selected;
    public static int reportCounter = 0;
    private static int startInt = 19690720;
    private static int endInt = 20171031;
    private static List filteredDatabase = new Stack();
    // public DatabaseReference reports;

    /**
     * Updates filteredDatabase with data in the current date range
     */
    private static void filterDB() {
        filteredDatabase.clear();

        for(int i = 0; i < database.size(); i++) {
            Rat rat = (Rat) database.get(i);
            Log.d(TAG, rat.toString());
            String date = rat.getDate();

            SimpleDateFormat formatRat = new SimpleDateFormat("dd/MM/yy", Locale.US);
            SimpleDateFormat formatFilter = new SimpleDateFormat("yyyyMMdd", Locale.US);
            try {

                Date formattedDate = formatRat.parse(date);
                Date startFilter = formatFilter.parse(String.valueOf(startInt));
                Log.d(TAG,String.valueOf(startInt)+ "Start Date: "+ startFilter.toString());
                Date endFilter = formatFilter.parse(String.valueOf(endInt));
                Log.d(TAG,"Date: "+ formattedDate.toString());
                Log.d(TAG,String.valueOf(endInt)+"End Date: "+ endFilter.toString());
                if (!(formattedDate.compareTo(endFilter) > 0 || formattedDate.compareTo(startFilter) < 0)) {
                    filteredDatabase.add(rat);
                    if (rat != null) {
                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(rat.getLatitude(), rat.getLongitude()))
                                .title(Integer.toString(rat.getUniqueKey()))
                                .snippet(rat.getDate()));
                    }
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

        /**
         * Default implementation of DatePickerFragment
         * @param savedInstanceState contains the saved data if this activity is returning from the background
         * @return the created DatePickerDialog
         */
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

        /**
         * Provides the ability to act on the DatePicker information when it is set
         * @param view the DatePicker just used
         * @param year the year selected
         * @param month the month selected
         * @param day the day selected
         */
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            Log.d(TAG, "DatePicker" + String.valueOf(year) + String.valueOf(month) + String.valueOf(day));
        }

    }

    public static class StartDatePickerFragment extends DatePickerFragment{
        /**
         * DatePickerFragment set to the default start date
         * @param savedInstanceState contains the saved data if this activity is returning from the background
         * @return the created DatePickerDialog
         */
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

        /**
         * Sets startInt to the selected date
         * @param view the DatePicker just used
         * @param year the year selected
         * @param month the month selected
         * @param day the day selected
         */
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            startInt = year*10000 + month*100 + day;
            Log.d(TAG, "StartDatePicker" + String.valueOf(startInt));
            filterDB();
        }
    }

    public static class EndDatePickerFragment extends DatePickerFragment{
        /**
         * Sets endInt to the selected date
         * @param view the DatePicker just used
         * @param year the year selected
         * @param month the month selected
         * @param day the day selected
         */
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            super.onDateSet(view, year, month, day);
            endInt = year*10000 + month*100 + day;
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

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        //Firebase Checking if user is already logged in and track log in status
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };
//        mAuth = FirebaseAuth.getInstance();

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.7128, -74.0060), 10.0f));
        map.getUiSettings().setZoomControlsEnabled(true);
        Log.d(TAG, "Google Maps" + map.toString());
        filterDB();
    }

}


