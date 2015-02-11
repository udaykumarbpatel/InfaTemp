package com.release.ukumar.gcskickoff_2014;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ukumar on 2/10/2015.
 */
public class AsyncGetForeignCurrency extends AsyncTask<String, Void, Currency> {
    ResultsPassing results;

    public AsyncGetForeignCurrency(ResultsPassing results) {
        this.results = results;
    }

    @Override
    protected Currency doInBackground(String... params) {

        String urlString = params[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();
                }


                Currency currency_obj = CurrencyUtil.CurrencyJSONParser.parseCurrency(sb.toString());
                return currency_obj;
            }
        } catch (MalformedURLException e) {
            Log.d("demo", e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("demo", e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("demo", e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Currency currency) {
        if (currency != null) {
            results.getResult(currency);
        }
    }

    public interface ResultsPassing {
        public void getResult(Currency result);
    }

}
