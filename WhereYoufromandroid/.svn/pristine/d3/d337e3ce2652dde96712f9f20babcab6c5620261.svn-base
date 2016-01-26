package com.example.karthikslaptop.wuatest1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.util.regex.Pattern;

public class PeopleInterest extends AppCompatActivity {
    //Menu for either the people or next page.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peopleinterest_design);
        String Name = getIntent().getStringExtra("Name");
        //Button commnds to move on while passing name through
        Button btnSimple = (Button) findViewById(R.id.BtnPeople);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = getIntent().getStringExtra("Name");
                Intent intent = new Intent(v.getContext(), People.class);
                intent.putExtra("Name", Name);
                startActivityForResult(intent, 0);
            }
        });
        Button btnSimple1 = (Button) findViewById(R.id.BtnInterest);
        btnSimple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = getIntent().getStringExtra("Name");
                String Re = getIntent().getStringExtra("Re");
                Intent intent = new Intent(v.getContext(), NewsPlaces.class);
                intent.putExtra("Name", Name);
                intent.putExtra("re",Re);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
