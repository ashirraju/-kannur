package com.example.hp.grid;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TouristPlaces extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    double lat, lon;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    float zoomLevel = 10.0f;



    double payyambalam_lat=11.869239;
    double Payyambalam_lon=75.3502536;
    double kannur_fort_lat=11.8541418;
    double kannur_fort_lon=75.3692568;
    double kanjirakkoli_lat=12.1422041;
    double kanjirakkoli_lon=75.6243521;
    double paithalmala_lat=12.1664427;
    double paithalmala_lon=75.5558538;

    LocationManager locationManager;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_places);


        checkLocationPermission();


        GPSTracker mGPS = new GPSTracker(TouristPlaces.this);
        if (mGPS.canGetLocation) {
            mGPS.getLocation();
            lat = mGPS.getLatitude();
            lon = mGPS.getLongitude();
            Toast.makeText(TouristPlaces.this, "" + lat + "\n" + lon, Toast.LENGTH_SHORT).show();

        }

        button = (Button) findViewById(R.id.place);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distance(lat,lon,kannur_fort_lat,kannur_fort_lon);
                Intent intent = new Intent(TouristPlaces.this, MyPlace.class);
                startActivity(intent);
            }
        });


        button = (Button) findViewById(R.id.zoom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomLevel++;
                LatLng user_location = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(user_location).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.myloc)));
                //  mMap.addMarker(new MarkerOptions().position(user_location).title("My place"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, zoomLevel));
            }
        });

        button = (Button) findViewById(R.id.zoomout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zoomLevel--;
                LatLng user_location = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(user_location).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.myloc)));
                //  mMap.addMarker(new MarkerOptions().position(user_location).title("My place"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user_location, zoomLevel));

            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(TouristPlaces.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        // locationManager.requestLocationUpdates();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        //This goes up to 21


        LatLng payyambalam = new LatLng(11.869239, 75.3502536);
        mMap.addMarker(new MarkerOptions().position(payyambalam).title("payyambalam beach"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(payyambalam, zoomLevel));


        LatLng kannur_fort = new LatLng(11.8541418, 75.3692568);
        mMap.addMarker(new MarkerOptions().position(kannur_fort).title("St. Angelo Fort"));
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kannur_fort, zoomLevel));


        LatLng kanjirakkoli = new LatLng(12.1422041, 75.6243521);
        mMap.addMarker(new MarkerOptions().position(kanjirakkoli).title("Kanjirakkolli Hill Station"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kanjirakkoli, zoomLevel));

        LatLng paithalmala = new LatLng(12.1664427, 75.5558538);
        mMap.addMarker(new MarkerOptions().position(paithalmala).title("Paithalmala Watch Tower"));
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paithalmala, zoomLevel));




    }



    public double distance(double lat1, double lon1, double lat2, double lon2) {


        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        Toast.makeText(TouristPlaces.this, ""+dist, Toast.LENGTH_SHORT).show();
        return (dist);




    }
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }




    }

