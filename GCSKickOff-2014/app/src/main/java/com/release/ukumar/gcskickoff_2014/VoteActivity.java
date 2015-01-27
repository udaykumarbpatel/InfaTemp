package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class VoteActivity extends Activity {
    RadioGroup radioGroup;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        String jsonData = getIntent().getExtras().getString("json");
        if (jsonData.equals("nothing"))
        {
            onBackPressed();
        }

        TextView ques = (TextView) findViewById(R.id.question);

        try {
            JSONObject notification = new JSONObject(jsonData);
            String question = notification.getString("question");
            ques.setText(question);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "No Questions to Vote", Toast.LENGTH_SHORT).show();
        }

        Button submit_answer = (Button) findViewById(R.id.submit_answer);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        submit_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == -1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Select a Option", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (id == R.id.radioButton) {
                        postData("Option 1");
                    }
                    if (id == R.id.radioButton2) {
                        postData("Option 2");
                    }
                    if (id == R.id.radioButton3) {
                        postData("Option 3");
                    }
                    if (id == R.id.radioButton4) {
                        postData("Option 4");
                    }
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Vote Submitted!", Toast.LENGTH_SHORT);
                toast.show();
                radioGroup.clearCheck();
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void postData(String option) {
        final String answer = option;
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();
                String fullUrl = "https://docs.google.com/forms/d/1TjL9SUuTVrV43oyY7WdvVnqvOc0KnluVjGIp5ko4Mvc/formResponse";
                HttpPost request = new HttpPost(fullUrl);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("entry.1302740522", answer));
                try {
                    UrlEncodedFormEntity formParams = new UrlEncodedFormEntity(params);
                    request.setEntity(formParams);
                    HttpResponse response = client.execute(request);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void progressstart(String str) {
        progressDialog = ProgressDialog.show(this, null, str);
        progressDialog.setCancelable(false);
    }

    public void progressdismiss() {
        progressDialog.dismiss();
    }

}
