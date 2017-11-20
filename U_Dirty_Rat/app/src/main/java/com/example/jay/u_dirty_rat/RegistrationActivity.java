package com.example.jay.u_dirty_rat;

import android.util.Log;


import android.support.v7.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Registers users and puts them into the Fire base database
 * Created by Team TBD
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private CheckBox adminInput;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameInput = (EditText) findViewById(R.id.username_registration_input);
        passwordInput = (EditText) findViewById(R.id.password_registration_input);
        adminInput = (CheckBox) findViewById(R.id.admin);
        // Fire base initialize auth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                startActivity(new Intent(RegistrationActivity.this, MainPage.class));
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };
        Button cancelButton = (Button) findViewById(R.id.registration_cancel_button);

        cancelButton.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this, WelcomeScreen.class)));

        Button registrationButton = (Button) findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(view -> registerUser());
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

    /**
     * check validity of the username.
     * @param in the username that has been input
     * @return boolean value. return true if it is valid.
     */
    public boolean isUsernameValid(String in) {
        String[] nameAndAddr = in.split("@");
        //check if it has 1 @ in the middle.
        if (nameAndAddr.length == 2){
            String further = nameAndAddr[1];
            String[] fin = further.split("\\.");
            // check if it has 1 . in the back
            if (fin.length == 2) {
                //check if there is something between @ and .
                if (!fin[0].equals("")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Attempts to register a new user. If successful, sends logged in user to MainPage
     */
    private void registerUser() {
        String email = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        @SuppressWarnings("UnusedAssignment") boolean isAdmin = adminInput.isChecked();


         // Checks to make sure fields are filled out correctly

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }

         // Checks to make sure fields are filled out correctly

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        //  Checks if email is in right format.

        if(!isUsernameValid(email)) {
            Toast.makeText(this, "Username is invalid type.", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Toast.makeText(RegistrationActivity.this, R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
