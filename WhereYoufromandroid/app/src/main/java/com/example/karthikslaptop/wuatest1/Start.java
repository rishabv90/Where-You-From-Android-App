package com.example.karthikslaptop.wuatest1;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class Start extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    //Initializers for MediaPlayer
    public static MediaPlayer music;
    public static Switch switch1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_design);
        // Change Font
        Typeface myTypeFace = Typeface.createFromAsset(getAssets(), "Going Rogue.otf");
        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setTypeface(myTypeFace);

        //Switch for Music Player
        switch1 = (Switch)findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(this);

        //Button commands for moving to next page
        Button btnSimple = (Button) findViewById(R.id.Btn1);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUp.class);
                startActivityForResult(intent, 0);
            }
        });
        Button btnSimple2 = (Button) findViewById(R.id.Btn3);
        btnSimple2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Login.class);
                startActivityForResult(intent, 0);
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
    //Turns music on or off
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            music = MediaPlayer.create(this, R.raw.grace_behind_the_curtain);
            music.setLooping(true);
            music.start();
            Toast.makeText(getApplicationContext(), "Music is ON", Toast.LENGTH_SHORT).show();

        } else {
            music.setLooping(false);
            music.pause();
            Toast.makeText(getApplicationContext(),"Music is OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
