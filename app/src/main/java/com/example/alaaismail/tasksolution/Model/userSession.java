package com.example.alaaismail.tasksolution.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class userSession {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_FIRST_NAME = "FirstName";
    private static final String KEY_LAST_NAME = "LastName";
    private static final String IS_LOGEDIN = "is_login";

    private Context mcontext;

    private SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;


    public userSession(Context mcontext) {
        this.mcontext = mcontext;
        mSharedPreferences = mcontext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mSharedPreferences.edit();

    }


    public static void loginUser(String username, boolean is_login, String first_name, String last_name) {
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_FIRST_NAME, first_name);
        mEditor.putString(KEY_LAST_NAME, last_name);
        mEditor.putBoolean(IS_LOGEDIN, is_login);


        mEditor.commit();
    }
}
