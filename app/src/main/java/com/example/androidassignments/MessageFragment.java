package com.example.androidassignments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MessageFragment extends Fragment {
    private Button deleteButton;
    private TextView messageText;
    private TextView idText;
    private String m;
    private int k;
    private int l;
    ChatWindow chatWindow;
    Configuration configuration;
    public MessageFragment(@Nullable ChatWindow window ) {
        chatWindow=window;
    }
//
//    private buttonSelectedListener listener;
//
//    public interface buttonSelectedListener {
//         void buttonClick(int id);
//    }
//
//    public void onAttach(Context context) {
//
//        super.onAttach(context);
//        if(context instanceof buttonSelectedListener){
//            listener = (buttonSelectedListener) context;
//        }
//        else{
//            throw new ClassCastException(context.toString()
//                    + " something wrong here");
//        }
//    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(savedInstanceState == null){
//            // Get back arguments
//            if(getArguments() != null) {
//                k = getArguments().getInt("Key", 0);
//                m = getArguments().getString("Message");
//                l = getArguments().getInt("LandOrNot");
//            }
//        }
    }
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_layout, container,false);

        messageText = layout.findViewById(R.id.message_detail);
        idText = layout.findViewById(R.id.message_id);
        deleteButton =layout.findViewById(R.id.DeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String key = idText.getText().toString();
//                if (l==0){
//                    Intent intent = new Intent(getActivity(),MessageDetails.class);
//                    intent.putExtra("DeleteKey",key);
//                    Log.i("sb", "onClick: " + key);
//                    getActivity().setResult(Activity.RESULT_OK,intent);
//                    getActivity().finish();
//                }
//                else{
//
//
//                    int id = Integer.parseInt(key);
////                    listener.buttonClick(id);
//
//                    Log.i("sb", "onClick: " + key);
//
//                }
                if (chatWindow ==(null)){
                    Intent data = new Intent();
                    data.putExtra("DeleteKey",Integer.parseInt(idText.getText().toString()));
                    getActivity().setResult(88,data);
                    getActivity().finish();
                }

                else{
                    chatWindow.deleteMsg(Integer.parseInt(idText.getText().toString()));
                }
            }
        });
//        messageText.setText(m);
//        idText.setText(String.valueOf(k));
        messageText.setText(getArguments().getString("Message"));
        idText.setText(String.valueOf(getArguments().getLong("Key")));
        return layout;
    }

}
