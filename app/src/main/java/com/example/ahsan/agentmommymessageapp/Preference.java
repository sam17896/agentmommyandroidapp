package com.example.ahsan.agentmommymessageapp;

/**
 * Created by AHSAN on 7/10/2018.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

public class Preference {
    // Shared preferences file name
    private static final String PREF_NAME = "Mommy I am lost";
    private static final String NUMBER = "number";
    private static final String MESSAGE = "message";


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    int PRIVATE_MODE = 0;


    public Preference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public String getNumber() {
        return pref.getString(NUMBER, "");
    }

    public void setNumber(String number) {
        editor.putString(NUMBER, number);
        editor.commit();
    }

    public String getMessage(){
        return pref.getString(MESSAGE,"");
    }

    public void setMessage(String message) {
        editor.putString(MESSAGE, message);
        editor.commit();
    }


}