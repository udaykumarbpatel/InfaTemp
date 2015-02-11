package com.release.ukumar.gcskickoff_2014;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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

        String answer = params[0];
        HttpClient client = new DefaultHttpClient();
        List<NameValuePair> params1 = new ArrayList<>();
        params1.add(new BasicNameValuePair("a", "1"));
        params1.add(new BasicNameValuePair("from", "EUR"));
        params1.add(new BasicNameValuePair("to", answer));

        BufferedReader in = null;

        try {

            URI uri = URIUtils.createURI("https", "www.google.com", -1,
                    "finance/converter",
                    URLEncodedUtils.format(params1, "UTF-8"), null);
            HttpGet request = new HttpGet(uri);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                in = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));
                String sb = "";
                String line = "";
                while ((line = in.readLine()) != null) {
                    if (line.contains("<div id=currency_converter_result>1 EUR = <span class=bld>"))
                        sb = sb + line;
                }
                String[] first_split = sb.split(">");
                String[] second_split = first_split[2].split("<");

                Currency curr = new Currency();
                curr.setCurrency_code(answer);
                curr.setCurrency_rate(second_split[0]);
                in.close();
                return curr;
            } else {
                Log.d("demo", "Error");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
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
