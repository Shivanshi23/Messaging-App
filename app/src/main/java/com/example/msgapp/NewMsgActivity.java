
package com.example.msgapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewMsgActivity extends AppCompatActivity {
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

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMsg();
            }
        });

    }
    private void saveMsg(){
        String phone = et_phone.getText().toString();
        String msg = et_msg.getText().toString();
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
