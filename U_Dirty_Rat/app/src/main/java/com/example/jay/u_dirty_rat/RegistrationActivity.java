package com.example.jay.u_dirty_rat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private CheckBox adminInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameInput = (EditText) findViewById(R.id.username_registration_input);
        passwordInput = (EditText) findViewById(R.id.password_registration_input);
        adminInput = (CheckBox) findViewById(R.id.admin);

        Button cancelButton = (Button) findViewById(R.id.registration_cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, WelcomeScreen.class));
            }
        });

        Button registrationButton = (Button) findViewById(R.id.registration_button);
        registrationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    /**
     * Attempts to register a new user. If successful, sends logged in user to MainPage
     */
    private void registerUser() {
        String email = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        boolean isAdmin = adminInput.isChecked();

        /**
         * Checks to make sure fields are filled out correctly
         */
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        /**
         * Checks to make sure fields are filled out correctly
         */
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        /**
         * Checks if that username is taken already
         */
        if(UserDB.contains(email)) {
            Toast.makeText(this, "That username is taken", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDB.add(email, password, isAdmin);
        startActivity(new Intent(RegistrationActivity.this, MainPage.class));

    }

}
