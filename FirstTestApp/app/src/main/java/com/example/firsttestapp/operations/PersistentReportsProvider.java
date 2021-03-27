package com.example.firsttestapp.operations;

import android.content.Context;
import android.util.Log;

import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.ReportData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersistentReportsProvider {

    private static final String REPORT_ARRAY_KEY = "REPORT_ARRAY_KEY";
    private static final String TAG = PersistentReportsProvider.class.getName();

    public static void storeReport(Context context, ReportData report) {
        Gson gson = new Gson();
        String json = gson.toJson(report);
        String result = PermanentStorage.getInstance().retrieveString(context, REPORT_ARRAY_KEY);
        if (!result.isEmpty()) {
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = new JSONArray(result);
                array.put(obj);
                PermanentStorage.getInstance().storeString(context, REPORT_ARRAY_KEY, array.toString());

            } catch (JSONException ex) {
                Log.e(TAG, ex.getMessage());
            }
        } else {
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray array = new JSONArray();
                array.put(obj);
                PermanentStorage.getInstance().storeString(context, REPORT_ARRAY_KEY, array.toString());

            } catch (JSONException ex) {
                Log.e(TAG, ex.getMessage());
            }
        }
    }

    public static List<ReportData> getReports(Context context) {
        String result = PermanentStorage.getInstance().retrieveString(context, REPORT_ARRAY_KEY);
        if (result.isEmpty()){
            return new ArrayList<>();
        } else {
            try {
                Gson gson = new Gson();
                return gson.fromJson(result, new TypeToken<List<ReportData>>() {
                }.getType());
            } catch (JsonSyntaxException ex) {
                Log.e(TAG, ex.getMessage());
                return new ArrayList<>();
            }
        }
    }
}
