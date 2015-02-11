package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MallActivity extends Activity implements AsyncGetPlaces.ResultsPassing{

    ProgressDialog progressDialog;
    ArrayList<Places> final_result;
    ItemListBaseAdapter places_list_adapter;
    ListView list_view;
    LocationManager locationManager;
    String NAME_KEY = "name";

    LocationListener simpleLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            new AsyncGetPlaces(MallActivity.this).execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + location.getLatitude()
                    + ","
                    + location.getLongitude()
                    + "&radius=17000&types=shopping_mall&key=AIzaSyC2VQupr4r5FAaZbR4bx02ugqUQdGJujEM");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        list_view = (ListView) findViewById(R.id.listView1);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Network not enabled")
                    .setMessage("Would like to enable the Location Services? ")
                    .setCancelable(true)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Intent i = new Intent(
                                            Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(i);
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                    finish();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 60000, 10,
                    simpleLocationListener);
            ProgressDialogStart("Loading Places to Visit....");

        }
    }

    @Override
    public void getResult(final ArrayList<Places> result) {
        progressDialogStop();
        this.final_result = result;

        places_list_adapter = new ItemListBaseAdapter(this, result);

        list_view.setAdapter(places_list_adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Uri uri = Uri.parse("http://www.google.com/#q=" + result.get(position).getName());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public void response_error() {
        Toast.makeText(this, "No Results found !!! ", Toast.LENGTH_LONG).show();
    }


    public void ProgressDialogStart(String str) {
        progressDialog = ProgressDialog.show(this, null, str);
        progressDialog.setCancelable(false);
    }

    public void progressDialogStop() {
        progressDialog.dismiss();
    }
}
