package com.example.su_ange.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.type;

public class Hotels extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listview;
    private ArrayAdapter<String> arrayad;
    private ArrayList<String> arrayList;
    private TextView selection;
    private String[]items={"Hotel1","Hotel2","Hotel3"};
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        listview = (ListView) findViewById(R.id.list);
        web = (WebView) findViewById(R.id.web);

        // Set listener
        listview.setOnItemClickListener(this);

        // Create an ArrayAdapter from List
        arrayad = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, items);

        // DataBind ListView with items from ArrayAdapter
        listview.setAdapter(arrayad);
        arrayad.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //String text = items[i];
        //selection.setText("Book"+text+" on Tripadvisor!");
        web.getSettings().setJavaScriptEnabled(true);
        //web.loadUrl(items[i]);
        web.loadUrl("https://www.tripadvisor.com/Hotel_Review-g187147-d197563-Reviews-La_Manufacture-Paris_Ile_de_France.html");


    }
}
