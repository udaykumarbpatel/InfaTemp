package com.example.ukumar.infapushtemp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParsePush;


public class SendPushActivity extends Activity {
    EditText tx1;
    TimePicker tp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_push);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.MESSAGE);

        tx1 = (EditText) findViewById(R.id.editText);
        tp1 = (TimePicker) findViewById(R.id.timePicker);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tx1.getText() == null)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please provide Input!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    String input = tx1.getText().toString();
                    ParsePush push = new ParsePush();
                    push.setMessage(input);
                    push.setChannel("Everyone");
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
