package com.release.ukumar.gcskickoff_2014;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ukumar on 2/10/2015.
 */
public class Currency {
    String currency_code;
    String Currency_rate;

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency_rate() {
        return Currency_rate;
    }

    public void setCurrency_rate(String currency_rate) {
        Currency_rate = currency_rate;
    }
}
