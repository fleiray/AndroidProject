package com.example.androidassignments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    private EditText dialogInput;
    private String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        messageText="You selected item 1";
        dialogInput = (EditText)findViewById(R.id.dialogInput_editText);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a custom Snackbar", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

    }
    public boolean onCreateOptionsMenu (Menu m){
        getMenuInflater().inflate(R.menu.menu_main, m );
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch(id){
            case R.id.action_one:
                Log.d("Toolbar", "Option 1 selected");

                Snackbar.make(findViewById(R.id.toolbar), messageText, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                break;
            case R.id.action_two:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.toolbar_dialogtitle);
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case R.id.action_three:
                LayoutInflater inflater = this.getLayoutInflater();
                AlertDialog.Builder custombuilder = new AlertDialog.Builder(this);
                custombuilder.setView(inflater.inflate(R.layout.custom_dialog,null));
                custombuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
//                        Log.d("Toolbar", dialogInput.getText().toString());

                    }
                });
                custombuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                // Create the AlertDialog
                AlertDialog Customdialog = custombuilder.create();
                Customdialog.show();
                break;
            case R.id.action_about:
                Toast toast = Toast.makeText(this , "Version 1.0, by "+getResources().getString(R.string.MYNAME), Toast.LENGTH_SHORT); //this is the ListActivity
                toast.show(); //display your message box
                break;
        }
        return true;
    }

}