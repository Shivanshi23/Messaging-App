package com.example.msgapp;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IndividualChatActivity extends AppCompatActivity {

    EditText messageBodyET;
    Button sendMsgButton;
    private MsgRepository repo;
    private MsgRepository repo1;
    private String contactNumber;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_chat);
        Intent i = getIntent();
        contactNumber = i.getStringExtra("contactNumber");
        this.setTitle(contactNumber);

        messageBodyET = (EditText)findViewById(R.id.typeMessageET);
        sendMsgButton = (Button)findViewById(R.id.sendMessageButton);

        Log.i(TAG, "Contact Number is "+contactNumber);
        Toast.makeText(this, "contact number is "+contactNumber, Toast.LENGTH_LONG).show();

        repo = new MsgRepository(getApplication());
        repo1 = new MsgRepository(getApplication());

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.individualChatRecyclerView);
        final IndividualChatAdapter adapter = new IndividualChatAdapter(this);

        recyclerView.setAdapter(adapter);

        repo1.getAll_t2(contactNumber).observe(this, new Observer<List<Msg>>() {
            @Override
            public void onChanged(@Nullable List<Msg> individualChatList) {
                adapter.setChatNotes(individualChatList);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void sendMessage(View view){
        String messageBody = messageBodyET.getText().toString();

        SmsManager sms = SmsManager.getSmsManagerForSubscriptionId(SubscriptionManager.getDefaultSubscriptionId());
        Log.i(TAG,"SubscriptionManager.getDefaultSmsSubscriptionId "+SubscriptionManager.getDefaultSubscriptionId());
        sms.sendTextMessage(contactNumber,null,messageBody,null,null);


        Main allChatSummaryEntity = new Main(contactNumber,messageBody);
        repo.insert_t1(allChatSummaryEntity);

        Msg individualChatEntity = new Msg(contactNumber,messageBody,"sent");
        repo1.insert_t2(individualChatEntity);

        messageBodyET.setText("");

        Toast.makeText(this, "Message Sent", Toast.LENGTH_LONG);

    }
}
