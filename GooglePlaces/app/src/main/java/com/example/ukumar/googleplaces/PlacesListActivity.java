package com.example.ukumar.googleplaces;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class PlacesListActivity extends Activity implements AsyncGetPlaces.ResultsPassing {

    ProgressDialog progressDialog;
    ArrayList<Places> final_result, places;
    ItemListBaseAdapter places_list_adapter;
    ListView list_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        progressstart("Loading Places to Visit....");
        new AsyncGetPlaces(this).execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=48.2206849,16.3800599&radius=42000&types=amusement_park&key=AIzaSyDLM-DLQfIVDAMUrFZRF27iIK9utMVLFUI");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_places_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getResult(ArrayList<Places> result) {
        progressdismiss();
        this.final_result = result;
        places_list_adapter = new ItemListBaseAdapter(this, result);
        list_view.setAdapter(places_list_adapter);
    }


    public void progressstart(String str) {
        progressDialog = ProgressDialog.show(this, null, str);
        progressDialog.setCancelable(false);
    }

    public void progressdismiss() {
        progressDialog.dismiss();
    }
}
