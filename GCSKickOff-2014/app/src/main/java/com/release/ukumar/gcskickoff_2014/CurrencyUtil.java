package com.release.ukumar.gcskickoff_2014;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ukumar on 2/10/2015.
 */

public class CurrencyUtil {

    static public class CurrencyJSONParser {
        static Currency parseCurrency(String jsonString)
                throws JSONException {
            JSONObject tempObject = new JSONObject(jsonString);
            JSONObject tempJSONObject = tempObject.getJSONObject("rates");
            Currency temp = new Currency(tempJSONObject);
            return temp;
        }
    }

}