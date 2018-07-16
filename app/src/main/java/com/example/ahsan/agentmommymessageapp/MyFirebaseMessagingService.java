package com.example.ahsan.agentmommymessageapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

/**
 * Created by AHSAN on 7/9/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]

        android.support.v4.util.ArrayMap message = (android.support.v4.util.ArrayMap<String, String>) remoteMessage.getData();
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]
        String messageTxt = "Location: " + message.get("latitude") + " " + message.get("longitude") + " Zipcode: " + message.get("zipcode")
                + " he/she said: " + message.get("message");

        Context mContext = getApplicationContext();
        Preference pref = new Preference(mContext);


        if(message.get("to").equals(pref.getNumber())){
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(pref.getNumber(), null, messageTxt, null, null);
        }

//        SmsManager smsManager = SmsManager.getDefault();
//        byte[] messageInBytes = messageTxt.getBytes();
//        smsManager.sendDataMessage("03322896908", null,(short) 8095, messageInBytes , null, null);

        Log.d("MESSAGE", messageTxt);

        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
       // sendSMS("03328287820", messageTxt);


        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }



    private void sendSMS(String phoneNumber, String message) {
        Context mContext = getApplicationContext();
        Preference pref = new Preference(mContext);
        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(mContext, 0,
                new Intent(mContext, SmsSentReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(mContext, 0,
                new Intent(mContext, SmsDeliveredReceiver.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
//            Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
        }

    }
    // [END receive_message]
}
