package com.alford.bushidoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.message;

public class MessageActivity extends AppCompatActivity {

    private DatabaseReference myRef;
    private EditText mEditText;
    private Button mSendMessageButton;
    private List<MessageObject> mMessageList;
    private RecyclerView mRecyclerView;
    private MessengerAdapter mAdapter;
    private String mId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mEditText = (EditText) findViewById(R.id.EDITOR);
        mSendMessageButton = (Button) findViewById(R.id.SEND_BTN);
        mRecyclerView =(RecyclerView) findViewById(R.id.MESSENGER);
        mMessageList = new ArrayList<>();

        //Todo Read messages from firebase
        // mId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new MessengerAdapter(mMessageList);
        mRecyclerView.setAdapter(mAdapter);




        myRef = FirebaseDatabase.getInstance().getReference("projects")
                .child(getIntent().getStringExtra("key")).child("messages");

        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themessagebeingsent = mEditText.getText().toString();

                if (!themessagebeingsent.isEmpty()) {
                    MessageObject obj = new MessageObject(themessagebeingsent);
                    myRef.push().setValue(obj);
                }

                mEditText.setText("");
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageObject msg = dataSnapshot.getValue(MessageObject.class);
                // mMessageList.add(msg);
                //mAdapter.notifyDataSetChanged();
                //mAdapter.notifyItemInserted(mMessageList.size() -1);
                mAdapter.addMsg(msg);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
