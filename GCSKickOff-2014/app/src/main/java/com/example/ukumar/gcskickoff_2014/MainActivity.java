package com.example.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        // Inititalize the and Sign-in for the Parse
        ParseAnalytics.trackAppOpened(getIntent());
        super.onCreate(savedInstanceState);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        ParseInstallation.getCurrentInstallation().put("IMEI", telephonyManager.getDeviceId());
        ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Loaded!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed to retrieve your profile!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        Button send_task = (Button) findViewById(R.id.send_alert);
        Button places_visit = (Button) findViewById(R.id.places_visit);

        send_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendPushActivity.class);
                startActivity(intent);
            }
        });

        places_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlacesListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
