package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String EMAIL_ADDRESS = "email_address";
    private EditText email_field;
    private Button login_btn;
    private String email_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        email_field = (EditText)findViewById(R.id.username_field);

        login_btn = (Button)findViewById(R.id.loginbutton);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        email_text = sharedPreferences.getString(EMAIL_ADDRESS,"email@domain.com");
        email_field.setText(email_text);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(EMAIL_ADDRESS,email_field.getText().toString());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onRestart(){
        super.onRestart();
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }

    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onRestart()");
    }


    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}