package com.example.su_ange.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BookNowActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private String API_KEY;
    private Exception exception;
    private Button bookButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now);

        bookButton = (Button) findViewById(R.id.button3);
        bookButton.setOnClickListener(this);
    }


    protected String doInBackground(Void... urls) {
        editText = (EditText) findViewById(R.id.editText2);
        String location = editText.getText().toString();
        String coordinates = "42.33141,-71.099396";
        // Do some validation here
        API_KEY="3e2b9d16-c415-41f7-b882-2eb4a4cb074e";
        try {
            URL url = new URL("https://api.tripadvisor.com/api/partner/2.0/map/"+coordinates+"?key=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                Log.i("URL is", String.valueOf(url));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
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
        editText.setText(response);
        Log.i("INFO", response);
    }

    @Override
    public void onClick(View view) {
        String response = doInBackground();
        onPostExecute(response);

    }
}
