package com.example.su_ange.app;

import android.content.Intent;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private EditText editText;
    private String streetAddr, longLat;
    public static final String EXTRA_MESSAGE = "location";
    public static final String EXTRA_MESSAGE2 = "coordinates";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        // city from edit text loaded on enter key
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    streetAddr = editText.getText().toString();
                    try {
                        geocode();
                    }
                    catch(Exception e){
                        System.out.print("exception caught");
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void geocode() throws Exception{
        // forward geocoding
        Geocoder fwd = new Geocoder(this);
        android.location.Address location = fwd.getFromLocationName(streetAddr, 1 ).get(0);

        LatLng test = new LatLng(location.getLatitude(), location.getLongitude());
        Log.i("long lat",test.toString());
        longLat = test.toString();
    }

    @Override
    public void onClick(View view) {
        streetAddr = editText.getText().toString();
            try {
                geocode();
            }
            catch(Exception e){
                System.out.print("exception caught");
            }
        String loc = editText.getText().toString();
        Intent intent;
        intent = new Intent(this, VRE360.class);
        //intent.putExtra(EXTRA_MESSAGE, loc);
        intent.putExtra(EXTRA_MESSAGE2, longLat);
        startActivity(intent);

    }
}
