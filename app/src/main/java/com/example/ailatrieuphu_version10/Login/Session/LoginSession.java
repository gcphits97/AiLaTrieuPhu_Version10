package com.example.ailatrieuphu_version10.Login.Session;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginSession {
    public static final String LOGGED_IN_PREF = "logged_in_status";
    public static final String FULL_NAME = "full_name";
    public static final String USERNAME = "username";
    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //set the login status
    public static void setLoggedIn(Context context, boolean loggedIn, String username, String fullname) {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(USERNAME, username);
        editor.putString(FULL_NAME, fullname);
        editor.apply();
    }

    //get the login status
    public static boolean getLoggedInStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String getFullName(Context context) {
        return getPreferences(context).getString(FULL_NAME, "");
    }

    public static String getUsername(Context context) {
        return getPreferences(context).getString(USERNAME, "");
    }
}
