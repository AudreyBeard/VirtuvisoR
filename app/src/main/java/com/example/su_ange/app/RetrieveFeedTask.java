package com.example.su_ange.app;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by SU_ANGE on 11/11/2017.
 */

public class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

    private EditText editText;
    private String API_KEY;
    private Exception exception;
    private Button bookButton;

    protected void onPreExecute() {
       // editText.setText("");
    }


    protected String doInBackground(Void... urls) {
        String coordinates = "42.33141,-71.099396";
        // Do some validation here
        API_KEY="3e2b9d16-c415-41f7-b882-2eb4a4cb074e";
        try {
            //URL url = new URL("https://api.tripadvisor.com/api/partner/2.0/map/"+coordinates+"?key=" + API_KEY);
            URL url = new URL("http://api.tripadvisor.com/api/partner/2.0/location/187147/attractions?key=" + API_KEY);
            URLConnection urlConnection = url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (Exception e) {
                Log.i("Something went wrong ", e.toString());
            }
            throw new Exception();
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }
    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        //editText.setText(response);
        Log.i("INFO", response);
       // editText.setText(response)

        //decode JSON
        try {

            JSONObject obj = new JSONObject(response);

            String attraction = obj.getJSONObject("data").getString("name");
            Log.i("JSON", "title " + attraction);

            /**
             String desc = obj.getJSONObject("website").getString("siteDescription");
             Log.i("JSON", "description " + desc);
             String email = obj.getJSONObject("website").getString("contactEmail");
             Log.i("JSON", "email " + email);
             String phone = obj.getJSONObject("website").getString("contactPhoneNumber");
             Log.i("JSON", "phone " + phone);
             **/



           // Message msg = handler.obtainMessage();
            //msg.obj = attraction;
            //handler.sendMessage(msg);



        } catch (JSONException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }


}
