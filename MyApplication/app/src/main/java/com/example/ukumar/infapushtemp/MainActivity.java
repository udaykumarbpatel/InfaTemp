package com.example.ukumar.infapushtemp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;


public class MainActivity extends Activity {

    public static final String MESSAGE = "FLAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpened(getIntent());

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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

        Button button = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        Toast toast = Toast.makeText(getApplicationContext(), item + " is selected", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, SendPushActivity.class);
        intent.putExtra(MESSAGE, "MainActivity");
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    public void onStart() {
        super.onStart();
    }

}
