package com.example.msgapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
//    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
//    private static final String TAG = "SMS Broadcast Receiver";
//    private String TAG1 = this.getClass().getSimpleName();
//    String msg, phone;
//    public static String MESSAGE = "Message";
//    MsgViewModel mvm;
//
//    public static final String EXTRA_PHONE =
//            "com.example.msgapp.EXTRA_PHONE";
//    public static final String EXTRA_MSG =
//            "com.example.msgapp.EXTRA_MSG";
//
//    public void onReceive(Context context, Intent intent) {
//        //retrieves action to be performed and display on log
//        Log.i(TAG, "Intent received: " + intent.getAction());
//        Log.i(TAG1,"Message Recieved");
//        if (intent.getAction() == SMS_RECEIVED) {
//            //retrieves a map of extended data from the intent
//            Bundle dataBundle = intent.getExtras();
//            if (dataBundle != null) {
//                //creating pdu(protocol data unit) object which is used for transferring message
//                Object[] pdusObj = (Object[]) dataBundle.get("pdus");
//                final SmsMessage[] message = new SmsMessage[pdusObj.length];
//
//                for (int i = 0; i < pdusObj.length; i++) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        // for api 23 and higher
//                        String format = dataBundle.getString("format");
//                        message[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
//                    } else {
//                        message[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
//                    }
//                    //get message and phone number
//                    msg = message[i].getMessageBody();
//                    phone = message[i].getDisplayOriginatingAddress();
//                }
//                Log.i(TAG1,"Phone "+phone);
//                Log.i(TAG1, "Message Body "+msg);
////                Log.i(TAG, "timestamp "+timestamp);
//
//                Toast.makeText(context, "SMS from " +phone+"\nmsg"+msg, Toast.LENGTH_LONG).show();
//
////                Intent data = new Intent(context, MainActivity.class);
////                data.putExtra(EXTRA_PHONE,phone);
////                data.putExtra(EXTRA_MSG, msg);
////                data.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(data);
//
//                mvm = new MsgViewModel((Application) context);
//                Main m = new Main(phone, msg);
//                mvm.insert_t1(m);
//
//                Msg m2 = new Msg(phone,msg,"received");
//                mvm.insert_t2(m2);
//            }
//        }
//    }

    private String TAG = this.getClass().getSimpleName();

    MsgViewModel mvm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.i(TAG,"Message Recieved");
        if(bundle!=null){
            final Object[] pdusObj = (Object[])bundle.get("pdus");
            String format = bundle.get("format").toString();

            for(int i=0; i<pdusObj.length;i++){

                SmsMessage message = SmsMessage.createFromPdu((byte[])pdusObj[i],format);
                String recieverPhn = message.getDisplayOriginatingAddress();
                String messageBody = message.getDisplayMessageBody();
                Long timestamp = message.getTimestampMillis();

                Log.i(TAG,"Phone "+recieverPhn);
                Log.i(TAG, "Message Body "+messageBody);
                Log.i(TAG, "timestamp "+timestamp);

                mvm = new MsgViewModel((Application) context);
                Main m = new Main(recieverPhn,messageBody);
                mvm.insert_t1(m);
                Msg m2 = new Msg(recieverPhn,messageBody,"received");
                mvm.insert_t2(m2);

                Toast.makeText(context, "Message Received",Toast.LENGTH_LONG).show();

            }
        }
    }
}

