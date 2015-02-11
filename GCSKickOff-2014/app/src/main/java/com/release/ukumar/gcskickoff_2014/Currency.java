package com.release.ukumar.gcskickoff_2014;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ukumar on 2/10/2015.
 */
public class Currency {
    String error;
    String curr_inr,curr_usd,curr_jpy,curr_krw,curr_cad,curr_brl,curr_aud,curr_cny,curr_sgd,curr_mxn,curr_gbp;

    public Currency(JSONObject placesJSONObject) throws JSONException {
        this.curr_inr = placesJSONObject.getString("INR");
        this.curr_usd = placesJSONObject.getString("USD");
        this.curr_jpy = placesJSONObject.getString("JPY");
        this.curr_krw = placesJSONObject.getString("KRW");
        this.curr_cad = placesJSONObject.getString("CAD");
        this.curr_brl = placesJSONObject.getString("BRL");
        this.curr_aud = placesJSONObject.getString("AUD");
        this.curr_cny = placesJSONObject.getString("CNY");
        this.curr_sgd = placesJSONObject.getString("SGD");
        this.curr_mxn = placesJSONObject.getString("MXN");
        this.curr_gbp = placesJSONObject.getString("GBP");
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCurr_inr() {
        return curr_inr;
    }

    public void setCurr_inr(String curr_inr) {
        this.curr_inr = curr_inr;
    }

    public String getCurr_usd() {
        return curr_usd;
    }

    public void setCurr_usd(String curr_usd) {
        this.curr_usd = curr_usd;
    }

    public String getCurr_jpy() {
        return curr_jpy;
    }

    public void setCurr_jpy(String curr_jpy) {
        this.curr_jpy = curr_jpy;
    }

    public String getCurr_krw() {
        return curr_krw;
    }

    public void setCurr_krw(String curr_krw) {
        this.curr_krw = curr_krw;
    }

    public String getCurr_cad() {
        return curr_cad;
    }

    public void setCurr_cad(String curr_cad) {
        this.curr_cad = curr_cad;
    }

    public String getCurr_brl() {
        return curr_brl;
    }

    public void setCurr_brl(String curr_brl) {
        this.curr_brl = curr_brl;
    }

    public String getCurr_aud() {
        return curr_aud;
    }

    public void setCurr_aud(String curr_aud) {
        this.curr_aud = curr_aud;
    }

    public String getCurr_cny() {
        return curr_cny;
    }

    public void setCurr_cny(String curr_cny) {
        this.curr_cny = curr_cny;
    }

    public String getCurr_sgd() {
        return curr_sgd;
    }

    public void setCurr_sgd(String curr_sgd) {
        this.curr_sgd = curr_sgd;
    }

    public String getCurr_mxn() {
        return curr_mxn;
    }

    public void setCurr_mxn(String curr_mxn) {
        this.curr_mxn = curr_mxn;
    }

    public String getCurr_gbp() {
        return curr_gbp;
    }

    public void setCurr_gbp(String curr_gbp) {
        this.curr_gbp = curr_gbp;
    }
}
