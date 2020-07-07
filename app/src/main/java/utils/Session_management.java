package utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import activity.MainActivity;

import static config.BaseURL.IS_LOGIN;
import static config.BaseURL.KEY_EMAIL;
import static config.BaseURL.KEY_ID;
import static config.BaseURL.KEY_MOBILE;
import static config.BaseURL.KEY_USER;
import static config.BaseURL.PREFS_NAME;

public class Session_management {

    SharedPreferences prefs;

    SharedPreferences.Editor editor;

    Context ctx;
    int PRIVATE_MODE = 0;

    public Session_management(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREFS_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(IS_LOGIN, false);
    }

    public void createLoginSession(String user_id, String user_email, String user_name, String user_mobile) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, user_id);
        editor.putString(KEY_USER, user_name);
        editor.putString(KEY_EMAIL, user_email);
        editor.putString(KEY_MOBILE, user_mobile);
        editor.commit();
    }

    public HashMap<String, String> getUsersDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ID, prefs.getString(KEY_ID, null));
        user.put(KEY_EMAIL, prefs.getString(KEY_EMAIL, null));
        user.put(KEY_MOBILE, prefs.getString(KEY_MOBILE, null));
        user.put(KEY_USER, prefs.getString(KEY_USER, null));
        return user;
    }
    public void logoutSession() {
        editor.clear();
        editor.commit();



        Intent logout = new Intent(ctx, MainActivity.class);
        // Closing all the Activities
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ctx.startActivity(logout);
    }
}
