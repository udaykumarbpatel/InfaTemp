package com.example.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParsePush;


public class SendPushActivity extends Activity {

    EditText tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_push);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE);

        Log.d("Error", "Intent Received");

        tx1 = (EditText) findViewById(R.id.alert_text);
        Button send_alert = (Button) findViewById(R.id.alert);
        Log.d("Error", "Text and Button initialized");

        send_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Error", "Inside onclick listener");
                if (tx1.getText().length() <= 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide Input!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Log.d("Error", "Inside onclick listener - Else part");
                    String input = tx1.getText().toString();
                    ParsePush push = new ParsePush();
                    push.setMessage(input);
                    push.setChannel("");
                    push.setChannel("");
                    push.sendInBackground();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_push, menu);
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
