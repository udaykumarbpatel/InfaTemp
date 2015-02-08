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
import java.util.ArrayList;

/**
 * Created by ukumar on 2/7/2015.
 */

public class AsyncGetTemperature extends AsyncTask<String, Void, ArrayList<Temperature>> {
    ResultsPassing results;

    public AsyncGetTemperature(ResultsPassing results) {
        this.results = results;
    }

    @Override
    protected ArrayList<Temperature> doInBackground(String... params) {

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


                ArrayList<Temperature> locations = TempUtil.TempJSONParser
                        .parsePlaces(sb.toString());
                return locations;
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
    protected void onPostExecute(ArrayList<Temperature> temperatures) {
        if (temperatures != null) {
            results.getResult(temperatures);
        }
    }

    public interface ResultsPassing {
        public void getResult(ArrayList<Temperature> result);
    }

}