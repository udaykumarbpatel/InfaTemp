package com.release.ukumar.gcskickoff_2014;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ukumar on 2/7/2015.
 */
public class Temperature {

    String weather, description;
    double temp;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Temperature(JSONObject placesJSONObject) throws JSONException {
        this.description = placesJSONObject.getString("description");
        this.weather = placesJSONObject.getString("main");
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "weather='" + weather + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                '}';
    }

    public String getWeather() {

        return weather;
    }

    public String getDescription() {
        return description;
    }
}
