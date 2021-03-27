package com.example.firsttestapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PermanentStorage {

    public static final String USER_KEY = "user";
    public static final String PHONE_KEY = "phone";
    public static final String EMAIL_KEY = "email";
    public static final String ACCOUNT_ID_KEY = "random_account";
    public static final String COMPANY_KEY = "company";
    public static final String ADDRESS_KEY = "address";
    public static final String LOCATION_KEY = "location";
    public static final String MAKE_KEY = "make";
    public static final String MODEL_KEY = "model";
    public static final String SERIAL_NUMBER_KEY = "model";
    public static final String IMPRESSIONS_KEY = "impressions";
    public static final String GOOGLE_EMAIL_KEY = "gmail";
    public static final String GOOGLE_NAME_KEY = "gname";
    public static final String GOOGLE_GIVEN_NAME_KEY = "ggivenname";
    public static final String GOOGLE_FAMILY_NAME_KEY = "gfamilyame";
    public static final String GOOGLE_ID_KEY = "gid";





    private static PermanentStorage permanentStorage;

    public static synchronized PermanentStorage getInstance() {
        if (permanentStorage == null) {
            permanentStorage = new PermanentStorage();
        }
        return permanentStorage;
    }

    public void storeString(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String retrieveString(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String result = preferences.getString(key, "");
        return result;
    }


    }
