package com.example.registerentrysir.other;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dell on 18-01-2018.
 */

public class UtilityHelper {


    private static final String FILE_NAME = "STUDENTSURVRY_PREFERENCE";
    public static final String USER_NAME = "username";

    public void writeUser(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, username);
        editor.commit();
    }

    public boolean checkUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USER_NAME, null);
        return username == null;
    }

    public static boolean isAdmin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(USER_NAME, null);
        try {
            JSONObject jsonObject=new JSONObject(user);
            String role=jsonObject.getString("name");
            if(role.equals("admin"))
                return true;
            else
                return false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

   /* public boolean readUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, null);
        editor.commit();
    }*/

    }

