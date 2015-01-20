package com.example.ukumar.gcskickoff_2014;

/**
 * Created by ukumar on 1/10/2015.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncImageDownload extends AsyncTask<Object, Void, String> {
    URL imageUrl;
    ImageView imageView;
    Bitmap bitmapImg;

    @Override
    protected String doInBackground(Object... params) {
        try {
            imageView = (ImageView) params[0];
            imageUrl = new URL((String) params[1]);
            HttpURLConnection con = (HttpURLConnection) imageUrl
                    .openConnection();
            con.setDoInput(true);
            con.connect();
            InputStream is = con.getInputStream();
            bitmapImg = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        imageView.setImageBitmap(bitmapImg);

    }
}
