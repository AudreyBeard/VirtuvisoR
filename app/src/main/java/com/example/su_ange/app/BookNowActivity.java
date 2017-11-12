package com.example.su_ange.app;

import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

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


        /*Thread t = new Thread(background);
        t.start();*/
    }


/*
    //thread connects to the Yelp Api, gets response data, JSON search results,
    //places data into Log and sends messages to display data on UI
    Runnable background = new Runnable() {
        public void run(){

            new RetrieveFeedTask().execute();


        }
*/

    @Override
    public void onClick(View view) {
        new RetrieveFeedTask().execute();
        //editText.setText(response);
        //decode JSON

    }


}
