package com.release.ukumar.gcskickoff_2014;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ukumar on 2/7/2015.
 */
public class Temperature {

    String weather, description;
    double temp;

    public Temperature(JSONObject placesJSONObject) throws JSONException {
        this.description = placesJSONObject.getString("description");
        this.weather = placesJSONObject.getString("main");
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeather() {

        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
