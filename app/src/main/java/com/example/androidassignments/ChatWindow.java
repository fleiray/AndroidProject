package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatWindow extends AppCompatActivity {

    private Button sendButton;
    private EditText messageText;
    private ListView messageListView;
    private ArrayList<String> messageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        sendButton = (Button) findViewById(R.id.sendButton);
        messageText = (EditText) findViewById(R.id.editSendText);
        messageListView = (ListView) findViewById(R.id.MessageList);
        messageArray = new ArrayList<String>();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageArray.add(messageText.getText().toString());
                Arrays.toString(messageArray.toArray());
            }
        });

    }

   private class ChatAdapter extends ArrayAdapter<String>{

       public ChatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
           super(context, resource, objects);
       }

       public int getCount(){
           return messageArray.size();
       }

       public String getItem(int position){
           return messageArray.get(position);
       }


   }
}