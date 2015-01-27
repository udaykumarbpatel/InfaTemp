package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    final Context context = this;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        SharedPreferences prfs = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        String name = prfs.getString("Authentication_Status", "");

        if (!name.equalsIgnoreCase("password") && !name.equalsIgnoreCase("password1")) {
            builder();
        }

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
                Log.e("Error : " , jsonData);
                notification_title.setVisibility(View.VISIBLE);
                notification_message.setVisibility(View.VISIBLE);
                JSONObject notification = new JSONObject(jsonData);
                if(notification.has("question"))
                {
                    Intent intent1 = new Intent(getApplicationContext(), VoteActivity.class);
                    intent1.putExtra("json", jsonData);
                    startActivity(intent1);
                }
                String message = notification.getString("alert");
                notification_title.setText("Last Alert!!!! ");
                notification_message.setText(message);

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong with the notification", Toast.LENGTH_SHORT).show();
            }
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getDeviceId() != null) {
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

        send_task.setVisibility(View.INVISIBLE);

        if(name.equals("password1"))
        {
            send_task.setVisibility(View.VISIBLE);
        }

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
                intent.putExtra("json", "nothing");
                startActivity(intent);
            }
        });
    }

    void builder() {
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                result = userInput.getText() + "";
                                if (result.equals("password") || result.equals("password1")) {
                                    SharedPreferences preferences = getSharedPreferences("AUTHENTICATION_FILE_NAME", 0);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("Authentication_Status", result.toString());
                                    editor.commit();
                                } else {
                                    Toast.makeText(getApplicationContext(), " Incorrect Password ! ", Toast.LENGTH_SHORT).show();
                                    MainActivity.this.finish();
                                }

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
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
