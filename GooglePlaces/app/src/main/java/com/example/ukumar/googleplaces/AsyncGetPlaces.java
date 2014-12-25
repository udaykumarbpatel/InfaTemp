package com.example.ukumar.googleplaces;

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
 * Created by ukumar on 12/24/2014.
 */
public class AsyncGetPlaces extends AsyncTask<String, Void, ArrayList<Places>> {

    ResultsPassing results;

    public AsyncGetPlaces(ResultsPassing results) {
        this.results = results;
    }

    @Override
    protected ArrayList<Places> doInBackground(String... params) {

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


                ArrayList<Places> movies = MapUtil.PlacesJSONParser
                        .parsePlaces(sb.toString());
                return movies;
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
    protected void onPostExecute(ArrayList<Places> result) {
        if (result != null) {
            results.getResult(result);
        } else {
            Log.d("demo", "null result");
        }
    }

    public interface ResultsPassing {
        public void getResult(ArrayList<Places> result);
    }
}
