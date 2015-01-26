package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        // Inititalize the and Sign-in for the Parse
        ParseAnalytics.trackAppOpened(getIntent());
        TextView notification_title = (TextView) findViewById(R.id.notification_title);
        TextView notification_message = (TextView) findViewById(R.id.notification_message);

        notification_title.setVisibility(View.INVISIBLE);
        notification_message.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                String jsonData = extras.getString("com.parse.Data");
                notification_title.setVisibility(View.VISIBLE);
                notification_message.setVisibility(View.VISIBLE);
                JSONObject notification = new JSONObject(jsonData);
                String message = notification.getString("alert");
                notification_title.setText("Last Alert!!!! ");
                notification_message.setText(message);

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong with the notification", Toast.LENGTH_SHORT).show();
            }
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if(telephonyManager.getDeviceId() != null)
        {
            ParseInstallation.getCurrentInstallation().put("IMEI", telephonyManager.getDeviceId());
        } else {
            ParseInstallation.getCurrentInstallation().put("IMEI", "Samsung Tab 4");
        }
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
        Button agenda = (Button) findViewById(R.id.agenda);
        Button vote = (Button) findViewById(R.id.vote);

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

        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Agenda.class);
                startActivity(intent);
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoteActivity.class);
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
