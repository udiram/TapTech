package com.example.firsttestapp.operations;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.firsttestapp.data.PermanentStorage;
import com.example.firsttestapp.data.StapleDataHolder;
import com.example.firsttestapp.emailsending.GMailSender;

import java.lang.ref.WeakReference;

public class StapleEmailOperation extends AsyncTask< Void, Void, String> {
    private WeakReference<Context> contextWeakReference;

    public StapleEmailOperation(Context context) {

        contextWeakReference = new WeakReference<>(context);

    }
    @Override
    protected String doInBackground(Void... voids) {

        StapleDataHolder data = StapleDataHolder.getInstance();
        PermanentStorage storage = PermanentStorage.getInstance();


        String name = storage.retrieveString(contextWeakReference.get(), PermanentStorage.USER_KEY);
        String account = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.ACCOUNT_ID_KEY);
        String company = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.COMPANY_KEY);
        String phone = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.PHONE_KEY);
        String email = PermanentStorage.getInstance().retrieveString(contextWeakReference.get(), PermanentStorage.EMAIL_KEY);
        try {


            String subject =  "New Staple Request" + " " + " from:"  + name + " " + company;
            String body = "Make:" + data.getMake() + "\n" + "Model Number: " + data.getModelNumber() + "\n" + "Model: " + data.getModelType() + "\n" + "Staple Type: " + data.getStapleType() + "\n" + "Finisher Model: " + data.getFinisher() + "\n" + "Quantity: " + data.getQuantity()  + "\n" + phone + "\n" + email + "\n" + account + "\n" + " ship to: " + data.getAddress();

            GMailSender sender = new GMailSender("kalyanotsava@gmail.com", "Svbf2019");


            sender.sendMail(subject,

                    body, "kalyanotsava@gmail.com  ",


                    "sathya6876@gmail.com");


        } catch (Exception e) {


            Log.e("error", e.getMessage(), e);


            return "Email Not Sent";


        }


        return "Email Sent";


    }


    @Override


    protected void onPostExecute(String result) {


        Log.e("LongOperation", result + "");


    }


    @Override


    protected void onPreExecute() {


    }


    @Override


    protected void onProgressUpdate(Void... values) {


    }


}


