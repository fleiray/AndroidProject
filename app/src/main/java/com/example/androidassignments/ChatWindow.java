package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity{

    protected static final String ACTIVITY_NAME = "ChatWindow";
    private Button sendButton;
    private Button deleteButton;
    private EditText messageText;
    private ListView messageListView;
    private TextView messageID;
    public ArrayList<String> messageArray= new ArrayList<String>();
    public ChatDatabaseHelper dbHelper;
    SQLiteDatabase database;
    private Cursor cursor;
    private ChatAdapter messageAdapter;
    MessageFragment messageFragment;
    boolean wide = false;
    boolean wide2 = false;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat_window);
        dbHelper = new ChatDatabaseHelper(this);
        sendButton = (Button) findViewById(R.id.sendButton);
        messageText = (EditText) findViewById(R.id.editSendText);
        messageListView = (ListView) findViewById(R.id.MessageList);
        messageAdapter = new ChatAdapter(this);
        if (wide){
            messageFragment = new MessageFragment(ChatWindow.this);
//            Bundle args = new Bundle();
//            args.putInt("LandOrNot",1);
//            messageFragment.setArguments(args);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.messageDetailFrame,messageFragment);
//            fragmentTransaction.commit();
            Bundle args = new Bundle();
//            args.putLong("Key",0);
//            args.putString("Message",messageAdapter.getItem(0));
            messageFragment=new MessageFragment(ChatWindow.this);
            messageFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.messageDetailFrame,messageFragment);
            fragmentTransaction.commit();
        }
        messageListView.setAdapter(messageAdapter);
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String testMessage = getItemMessage(position);
//                int testId = getItemId(position);
//                if (findViewById(R.id.LayoutWithDualParts)==null) {
//                    Toast.makeText(getBaseContext(), "Called by list poistion: " + position, Toast.LENGTH_SHORT);
//                    Intent intent = new Intent(ChatWindow.this, MessageDetails.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("Key", testId);
//                    bundle.putString("Message", testMessage);
//                    bundle.putInt("LandOrNot",0);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, 11);
//                }
//                else{
//                    MessageFragment messageFragment = new MessageFragment(ChatWindow.this);
//                    Bundle args = new Bundle();
//                    args.putInt("Key", testId);
//                    args.putInt("LandOrNot",1);
//                    args.putString("Message", testMessage);
//                    messageFragment.setArguments(args);
//                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.messageDetailFrame,messageFragment);
//                    //fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
                Bundle args = new Bundle();
                args.putLong("Key",id);
                args.putString("Message",messageAdapter.getItem(position));
                messageFragment=new MessageFragment(ChatWindow.this);
                messageFragment.setArguments(args);
                if (wide){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.messageDetailFrame,messageFragment);
                    ft.commit();}
                else{
                    Intent intent = new Intent(ChatWindow.this,MessageDetails.class);
                    intent.putExtra("args",args);
                    startActivityForResult(intent,11);
                }
            }
        });
        wide = findViewById(R.id.LayoutWithDualParts)!=(null);
//        wide2 =  findViewById(R.id.messageDetailFrame)!=(null);
        database= dbHelper.getWritableDatabase();
        updateMessages();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                messageArray.add(messageText.getText().toString());
//                //Arrays.toString(messageArray.toArray());
//                messageAdapter.notifyDataSetChanged();
//
//                if(dbHelper.addMessage(messageText.getText().toString())){
//                    Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getBaseContext(),"Faliure",Toast.LENGTH_SHORT).show();
//                }
//                messageText.setText("");
                Log.i("Send Test", "sendMessageFunc: send function");
                String newMsg=messageText.getText().toString();
                messageArray.add(newMsg);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                messageText.setText("");
                ContentValues cValues= new ContentValues();
                cValues.put(ChatDatabaseHelper.KEY_MESSAGE,newMsg);
                database.insert(ChatDatabaseHelper.TableName,"NullPlaceHolder",cValues);
            }
        });

    }
    public void updateMessages(){
        messageArray = new ArrayList<>();
        cursor = database.query(false, "ChatTable", null, null, null, null, null, null, null);
        cursor.moveToFirst();
//        Log.i(ACTIVITY_NAME, "Cursor’s column count =" + cursor.getColumnCount() + " Column 1's name: " + cursor.getColumnName(0) + " Column 2's name: " + cursor.getColumnName(1));
//        Log.i(ACTIVITY_NAME, cursor.getColumnName(0) + " " + cursor.getColumnName(1) );
//
//        while(cursor.moveToNext()){
//            messageArray.add(cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
//                Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
//        }
//        if (findViewById(R.id.LayoutWithDualParts)!=null){
//            MessageFragment messageFragment = new MessageFragment(ChatWindow.this);
//            Bundle args = new Bundle();
//            args.putInt("LandOrNot",1);
//            messageFragment.setArguments(args);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.messageDetailFrame,messageFragment);
//            fragmentTransaction.commit();
//
//        }
        while
        (!cursor.isAfterLast()) {
            String msg = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            messageArray.add(msg);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" +msg );
            int count =cursor.getColumnCount();
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + count);
            for (int columnIndex=0;columnIndex<count;columnIndex++){
                Log.i(ACTIVITY_NAME, "Column: "+cursor.getColumnName(columnIndex));
            }
            cursor.moveToNext();
        }
    }
    public void deleteMsg(int id) {
        if (wide){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(messageFragment);
            ft.commit();
        }
        database.delete(ChatDatabaseHelper.TableName,ChatDatabaseHelper.KEY_ID+"="+id,null);
        updateMessages();
        messageAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (resultCode == 88) {
                //Get result
                int id = data.getIntExtra("DeleteKey",-1);
                Log.i("SB", "onActivityResult: "+ data.getStringExtra("DeleteKey"));
//                dbHelper.deleteMessage(id);
                //messageArray.remove(id-1);
                if (id != -1) {
                    deleteMsg(id);
                }
            }
//            else{
//                int id = Integer.parseInt(data.getStringExtra("DeleteKey"));
//                Log.i("SB", "onActivityResult: "+ data.getStringExtra("DeleteKey"));
//                dbHelper.deleteMessage(id);
//            }
        }
    }
//
//
//
//    @Override
//    public void buttonClick(int id) {
//        if (findViewById(R.id.LayoutWithDualParts)!=null) {
//            dbHelper.deleteMessage(id);
//            //messageArray.remove(id - 1);
//
//        }
//    }

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
           if(position % 2 == 0) {

               result = inflater.inflate(R.layout.chat_row_outgoing, null);
           }
           else {
               result = inflater.inflate(R.layout.chat_row_incoming, null);
           }
           TextView message = (TextView)result.findViewById(R.id.message_text);
           message.setText(getItem(position)); // get the string at position
           return result;
       }
        public long getItemId(int position){
            cursor= database.query(false, "ChatTable", null, null, null, null, null, null, null);
            Log.i("PositionValue", String.valueOf(position));
            cursor.moveToPosition(position);
            long k = cursor.getLong( cursor.getColumnIndex( ChatDatabaseHelper.KEY_ID));
            Log.i("THISISID",String.valueOf(k));
            return k;
        }

   }
//    public int getItemId(int position){
//        cursor.moveToPosition(position);
//        int ID = cursor.getInt(cursor.getColumnIndex(dbHelper.KEY_ID));
//        return ID;
//    }
//    public String getItemMessage(int position){
//        cursor.moveToPosition(position);
//        String message = cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE));
//        return message;
//    }
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
        if (wide){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(messageFragment);
            ft.commit();
        }
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
