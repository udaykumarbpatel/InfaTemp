package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
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

import java.util.ArrayList;


public class MainActivity extends Activity implements AsyncGetTemperature.ResultsPassing {


    final Context context = this;
    ArrayList<Temperature> final_result;
    TextView temperature;
    TextView weather;
    String Admin_Password = "infaadmin";
    String User_Password = "infagcs";
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        SharedPreferences prfs = getSharedPreferences("AUTHENTICATION_FILE_NAME", Context.MODE_PRIVATE);
        String name = prfs.getString("Authentication_Status", "");

        if (!name.equalsIgnoreCase(User_Password) && !name.equalsIgnoreCase(Admin_Password)) {
            builder();
        }

        // Inititalize the and Sign-in for the Parse
        ParseAnalytics.trackAppOpened(getIntent());
        TextView notification_title = (TextView) findViewById(R.id.notification_title);
        TextView notification_message = (TextView) findViewById(R.id.notification_message);
        temperature = (TextView) findViewById(R.id.temperature);
        weather = (TextView) findViewById(R.id.weather);

        notification_title.setVisibility(View.INVISIBLE);
        notification_message.setVisibility(View.INVISIBLE);

        String temp_url = "http://api.openweathermap.org/data/2.5/weather?q=Vienna,Austria&APPID=31d3692db1ebefffc2ce7599dd1726e8";

        new AsyncGetTemperature(MainActivity.this).execute(temp_url);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                String jsonData = extras.getString("com.parse.Data");
                JSONObject notification = new JSONObject("{\"alert\":\"-\"}");
                if(jsonData!= null) {
                    notification = new JSONObject(jsonData);
                }
                if (notification.has("question")) {
                    Intent intent1 = new Intent(getApplicationContext(), VoteActivity.class);
                    intent1.putExtra("json", jsonData);
                    startActivity(intent1);
                }
                String message = notification.getString("alert");
                notification_title.setVisibility(View.VISIBLE);
                notification_message.setVisibility(View.VISIBLE);
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
                    Toast toast = Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_SHORT);
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
        Button restaurant = (Button) findViewById(R.id.restaurant);
        Button malls = (Button) findViewById(R.id.malls_nearby);
        Button forex = (Button) findViewById(R.id.forex);


        send_task.setVisibility(View.INVISIBLE);

        if (name.equals(Admin_Password)) {
            send_task.setVisibility(View.VISIBLE);
        }

        send_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendPushActivity.class);
                startActivity(intent);
            }
        });

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RestuarantActivity.class);
                startActivity(intent);
            }
        });

        malls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MallActivity.class);
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

        forex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForeignExchange.class);
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
                                if (result.equals(User_Password) || result.equals(Admin_Password)) {
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
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getResult(ArrayList<Temperature> result) {
        this.final_result = result;
        temperature.setText("Temperature = " + ((int) (result.get(0).getTemp() - 273.15)) + "\u00b0 C");
        weather.setText(result.get(0).getWeather() + ", " + result.get(0).getDescription());
    }
}
