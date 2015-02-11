package com.release.ukumar.gcskickoff_2014;

import android.app.Activity;
import android.os.Bundle;
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

        tx1 = (EditText) findViewById(R.id.alert_text);
        Button send_alert = (Button) findViewById(R.id.alert);

        send_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tx1.getText().length() <= 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide Input!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String input = tx1.getText().toString();
                    ParsePush push = new ParsePush();
                    push.setMessage(input);
                    push.setChannel("");
                    push.sendInBackground();
                    tx1.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Alert Sent!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
