package com.release.ukumar.gcskickoff_2014;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ukumar on 2/7/2015.
 */
public class TempUtil {

    static public class TempJSONParser {
        static ArrayList<Temperature> parsePlaces(String jsonString)
                throws JSONException {
            ArrayList<Temperature> temp = new ArrayList<Temperature>();

            JSONObject tempObject = new JSONObject(jsonString);
            double temperature_now = tempObject.getJSONObject("main").getDouble("temp");

            JSONArray tempJSONArray = tempObject.getJSONArray("weather");

            for (int i = 0; i < tempJSONArray.length(); i++) {
                JSONObject tempJSONObject = tempJSONArray.getJSONObject(i);
                Temperature temp1 = new Temperature(tempJSONObject);
                temp1.setTemp(temperature_now);
                temp.add(temp1);
            }
            return temp;
        }
    }

}
