
package com.example.msgapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class NewMsgActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    public static final String EXTRA_PHONE =
            "com.example.msgapp.EXTRA_PHONE";
    public static final String EXTRA_MSG =
            "com.example.msgapp.EXTRA_MSG";


    private EditText et_phone;
    private EditText et_msg;
    private Button button_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_msg);

        et_phone = findViewById(R.id.et_phone);
        et_msg = findViewById(R.id.et_msg);
        button_send = findViewById(R.id.button_send);

        checkForSmsPermission();

    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "PERMISSION NOT GRANTED!");
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            sendMsg();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        // For the requestCode, check if permission was granted or not.
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted. Enable sms button.
                    sendMsg();
                } else {
                    // Permission denied.
                    Log.d(TAG, "PERMISSION NOT GRANTED!");
                    Toast.makeText(this, "PERMISSION NOT GRANTED!", Toast.LENGTH_SHORT).show();
                    // Disable the sms button.
                    return;
                }
            }
        }
    }

    public void sendMsg(){

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smsSendMessage();
            }
        });
    }

    private void smsSendMessage(){
        String phone = et_phone.getText().toString();
        if(phone.length() != 10){
            Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
            return;
        }
        String msg = et_msg.getText().toString();

        //for sending message
        checkForSmsPermission();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, msg,
                null, null);

        //for save data to database in calling file ,i.e, mainActivity file
        Intent data = new Intent();
        if(phone.trim().isEmpty() || msg.trim().isEmpty()){
            Toast.makeText(this, "Please enter valid details",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
        data.putExtra(EXTRA_PHONE, phone);
        data.putExtra(EXTRA_MSG, msg);
        setResult(RESULT_OK, data);
        }
        finish();
    }
}
