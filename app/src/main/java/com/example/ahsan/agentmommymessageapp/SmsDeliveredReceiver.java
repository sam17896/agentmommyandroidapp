package com.example.ahsan.agentmommymessageapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class SmsDeliveredReceiver extends BroadcastReceiver {

    private Context mContaxt;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        mContaxt = context;
        Preference preference = new Preference(mContaxt);
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "SMS delivered", Toast.LENGTH_SHORT).show();
                deleteSMS(mContaxt, preference.getMessage(), preference.getNumber());
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(context, "SMS not delivered", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void deleteSMS(Context context, String message, String number) {
    //    Context context = getApplicationContext();
//        try {
            //     mLogger.logInfo("Deleting SMS from inbox");
            Log.d("MESSAGE", number);
            Uri uriSms = Uri.parse("content://sms/sent");
            Cursor c = context.getContentResolver().query(uriSms,
                    new String[] { "_id", "thread_id", "address",
                            "person", "date", "body" }, null, null, null);

            if (c != null && c.moveToFirst()) {
                do {
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String body = c.getString(5);
                    if (message.equals(body) || address.equals(number)) {
                        //               mLogger.logInfo("Deleting SMS with id: " + threadId);

                        Log.d("MESSAGE", address);
                        Log.d("MESSAGE", body);
                        Log.d("MESSAGE", "" + id);

                        context.getContentResolver().delete(
                                Uri.parse("content://sms/" + id), null, null);
                    }
                } while (c.moveToNext());
            }
//        } catch (Exception e) {
//            Log.d("Message", e.getMessage());
//
//            //       mLogger.logError("Could not delete SMS from inbox: " + e.getMessage());
//        }
    }
}
