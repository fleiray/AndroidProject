package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "MainActivity";
    private Button main_btn;
    private Button chat_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(ACTIVITY_NAME, "In onCreate()");

        main_btn = (Button)findViewById(R.id.main_button);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }
        });

        chat_btn = (Button)findViewById(R.id.chat_button);

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME,"User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this,ChatWindow.class );
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==10)
        {
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");

            if (resultCode == Activity.RESULT_OK){
                String messagePassed = data.getStringExtra("Response");
                //String text = "ListItemsActivity passed: My information to share"
                //ListItemsActivity passed:
                Toast.makeText(this , getResources().getString(R.string.messagePass)+ messagePassed, Toast.LENGTH_LONG).show(); //this is the ListActivity
                 //display your message box
            }

        }

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