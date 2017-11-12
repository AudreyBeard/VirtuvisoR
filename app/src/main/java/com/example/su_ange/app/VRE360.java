package com.example.su_ange.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import android.widget.Button;
import android.widget.EditText;

import static com.example.su_ange.app.MainActivity.EXTRA_MESSAGE2;


public class VRE360 extends AppCompatActivity implements View.OnClickListener {

        // Use Geocoder to process English into LatLng
        //private final Geocoder geocoder = new Geocoder();
        //GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress()
        // Boston, MA
        private LatLng location;
        private Button findHotels;


        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vre360);
            findHotels = (Button) findViewById(R.id.button6);
            findHotels.setOnClickListener(this);
            Intent intent = getIntent();
            String latLngString = intent.getStringExtra(EXTRA_MESSAGE2);
            latLngString = latLngString.substring(10,latLngString.length()-1);
            String[] latLngParts = latLngString.split(",");
            location = new LatLng(Float.parseFloat(latLngParts[0]), Float.parseFloat(latLngParts[1]));
            
            SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                    (SupportStreetViewPanoramaFragment)
                            getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                    new OnStreetViewPanoramaReadyCallback() {
                        @Override
                        public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                            // Only set the panorama to BOSTON on startup (when no panoramas have been
                            // loaded which is when the savedInstanceState is null).
                            if (savedInstanceState == null) {
                                panorama.setPosition(location);
                            }
                        }
                    });
        }

        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(this, Hotels.class);
            //intent.putExtra(EXTRA_MESSAGE, loc);
            //intent.putExtra(EXTRA_MESSAGE2, longLat);
            startActivity(intent);
        }
    }
