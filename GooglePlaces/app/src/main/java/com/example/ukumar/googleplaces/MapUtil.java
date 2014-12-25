package com.example.ukumar.googleplaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ukumar on 12/24/2014.
 */
public class MapUtil {

    static public class PlacesJSONParser {
        static ArrayList<Places> parsePlaces(String jsonString)
                throws JSONException {
            ArrayList<Places> places = new ArrayList<Places>();

            JSONObject placeObject = new JSONObject(jsonString);
            JSONArray placesJSONArray = placeObject.getJSONArray("results");

            for (int i = 0; i < placesJSONArray.length(); i++) {
                JSONObject placeJSONObject = placesJSONArray.getJSONObject(i);
                Places place = new Places(placeJSONObject);
                places.add(place);
            }

            return places;
        }
    }

}

