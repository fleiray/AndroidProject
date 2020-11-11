package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    private Button sendButton;
    private EditText messageText;
    private ListView messageListView;
    private ArrayList<String> messageArray = new ArrayList<String>();
    private ChatDatabaseHelper dbHelper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        dbHelper = new ChatDatabaseHelper(this);

        sendButton = (Button) findViewById(R.id.sendButton);
        messageText = (EditText) findViewById(R.id.editSendText);
        messageListView = (ListView) findViewById(R.id.MessageList);
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        messageListView.setAdapter(messageAdapter);
        cursor = dbHelper.getMessages();
        Log.i(ACTIVITY_NAME, "Cursor’s column count =" + cursor.getColumnCount() + " Column 1's name: " + cursor.getColumnName(0) + " Column 2's name: " + cursor.getColumnName(1));
        Log.i(ACTIVITY_NAME, cursor.getColumnName(0) + " " + cursor.getColumnName(1) );

        if (cursor.moveToFirst()){
            do{
                messageArray.add(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
            } while(cursor.moveToNext());
        }
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageArray.add(messageText.getText().toString());
                //Arrays.toString(messageArray.toArray());
                messageAdapter.notifyDataSetChanged();

                if(dbHelper.addMessage(messageText.getText().toString())){
                    Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),"Faliure",Toast.LENGTH_SHORT).show();
                }
                messageText.setText("");
            }
        });
    }

   private class ChatAdapter extends ArrayAdapter<String>{

       public ChatAdapter(@NonNull Context context) {
            super(context, 0);
       }

       public int getCount(){

           return messageArray.size();
       }

       public String getItem(int position){
           return messageArray.get(position);
       }

       public View getView(int position, View convertView, ViewGroup parent){
           LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
           View result = null ;
           if(position%2 == 0) {

               result = inflater.inflate(R.layout.chat_row_outgoing, null);
           }
           else {
               result = inflater.inflate(R.layout.chat_row_incoming, null);
           }
           TextView message = (TextView)result.findViewById(R.id.message_text);
           message.setText(getItem(position)); // get the string at position
           return result;
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
        dbHelper.close();
    }
}
