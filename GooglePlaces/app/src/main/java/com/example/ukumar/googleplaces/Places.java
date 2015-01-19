package com.example.ukumar.googleplaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ukumar on 12/24/2014.
 */
public class Places {

    double lat, lng;
    String name;
    String open_hours;
    String photo_reference;

    public Places(JSONObject placesJSONObject) throws JSONException {
        this.lat = placesJSONObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
        this.lng = placesJSONObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
        this.name = placesJSONObject.getString("name");


        if (placesJSONObject.has("opening_hours")) {
            this.open_hours = placesJSONObject.getJSONObject("opening_hours").getString("open_now");
        } else {
            this.open_hours = "Not Available";
        }

        if (placesJSONObject.has("photos")) {
            this.photo_reference = placesJSONObject.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
        } else {
            this.photo_reference = "NA";
        }

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen_hours() {
        return open_hours;
    }

    public void setOpen_hours(String open_hours) {
        this.open_hours = open_hours;
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

}
