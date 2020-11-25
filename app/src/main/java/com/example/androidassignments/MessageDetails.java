package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MessageDetails extends AppCompatActivity{
    private String key_message;
    private int key_id;
    private int land;
    public MessageFragment messageFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
//        Bundle b = getIntent().getExtras();
//        key_id = b.getInt("Key");
//        key_message = b.getString("Message");
//        land = b.getInt("LandOrNot");
//
//        Bundle args = new Bundle();
//        args.putInt("Key", key_id);
//        args.putString("Message", key_message);
//        args.putInt("LandOrNot",land);
        Bundle args = getIntent().getBundleExtra("args");
        messageFragment = new MessageFragment(null);
        messageFragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_land, messageFragment);
        fragmentTransaction.commit();

//        Intent intent = new Intent(this,ChatWindow.class);
//        intent.putExtra("DeleteKey",messageFragment.position);
//        setResult(Activity.RESULT_OK, intent);
//        finish();
    }
//        @Override
//    public void buttonClick(int id) {
//            Intent intent = new Intent(this,ChatWindow.class);
//            intent.putExtra("DeleteKey",id);
//            Log.i("sb", "onClick: " + id);
//            setResult(888,intent);
//            finish();
//    }

    public void OnConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.i("TAG", "OnConfigurationChanged: LANDSCAPE");
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i("TAG", "OnConfigurationChanged: PORTRAIT");
        }
    }
}