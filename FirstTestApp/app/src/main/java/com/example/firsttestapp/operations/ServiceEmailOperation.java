package com.example.firsttestapp.operations;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.firsttestapp.callback.OperationResultCallback;
import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.ReportData;
import com.example.firsttestapp.data.ReportDataHolder;
import com.example.firsttestapp.emailsending.GMailSender;

import java.lang.ref.WeakReference;


/**
 * Created by GsolC on 2/24/2017.
 */


public class ServiceEmailOperation extends AsyncTask<Void, Void, String> {

    private static final String NOT_SENT = "EMAIL NOT SENT";
    private static final String SENT = "EMAIL SENT";

    private WeakReference<Context> contextWeakReference;
    OperationResultCallback callback;

    public ServiceEmailOperation(Context context, OperationResultCallback callback) {

        contextWeakReference = new WeakReference<>(context);
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {

        ReportData data = ReportDataHolder.getInstance().getReportData();
        PermanentStorage storage = PermanentStorage.getInstance();


        String name = storage.retrieveString(contextWeakReference.get(), PermanentStorage.USER_KEY);
        String account = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.ACCOUNT_ID_KEY);
        String company = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.COMPANY_KEY);
        String phone = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.PHONE_KEY);
        String email = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.EMAIL_KEY);


        try {


            String subject = data.getMake() + " " + data.getModelNumber() + " " + data.getModelType() + " " + data.getSerialNumber() + " " + data.getRequestType();
            String body = "description:" + data.getDescription() + "\n" + "current impressions:" + data.getMeterRead() + "\n" + name + "\n" + company + "\n" + account + "\n" + phone + "\n" + email + "\n" + " ship to: " + data.getAddress();

            GMailSender sender = new GMailSender("kalyanotsava@gmail.com", "Svbf2019");

            sender.sendMail(subject,
                    body, "kalyanotsava@gmail.com  ",

                    "sathya6876@gmail.com");


        } catch (Exception e) {


            Log.e("error", e.getMessage(), e);


            return NOT_SENT;

        }

        return SENT;
    }


    @Override
    protected void onPostExecute(String result) {

        Log.e("LongOperation", result + "");

        switch (result) {
            case SENT: {
                callback.onOperationSuccess();
                break;
            }
            case NOT_SENT: {
                callback.onOperationFailure();
                break;
            }
            default:
                callback.onOperationFailure();
        }

    }


    @Override

    protected void onProgressUpdate(Void... values) {


    }


}

