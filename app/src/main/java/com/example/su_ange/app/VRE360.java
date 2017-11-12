package com.example.su_ange.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import android.widget.EditText;


    public class VRE360 extends AppCompatActivity implements View.OnClickListener{

        // Use Geocoder to process English into LatLng
        //private final Geocoder geocoder = new Geocoder();
        //GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress()
        // Boston, MA
        private LatLng location;
        //private FloatingActionButton floatingActionButton;
        private Button nextAttrButton;

        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vre360);
            Intent intent = getIntent();
            String latLngString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
            latLngString = latLngString.substring(10,latLngString.length()-1);
            String[] latLngParts = latLngString.split(",");
            location = new LatLng(Float.parseFloat(latLngParts[0]), Float.parseFloat(latLngParts[1]));

            nextAttrButton = (Button) findViewById(R.id.nextAttrButton);
            //floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

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
            //floatingActionButton.setOnClickListener(this);
            nextAttrButton.setOnClickListener(this);
        }
        public void onClick(View view) {
            Intent intent;
            switch(view.getId()) {
                //case R.id.floatingActionButton:
                //    intent = new Intent(this, VRE360.class);
                //    startActivity(intent);
                case R.id.nextAttrButton:
                    intent = new Intent(this, VRE360.class);
                    startActivity(intent);
            }
        }

    }
