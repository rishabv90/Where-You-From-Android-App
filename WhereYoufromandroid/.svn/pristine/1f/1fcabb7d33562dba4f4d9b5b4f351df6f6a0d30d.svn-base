package com.example.karthikslaptop.wuatest1;


import android.content.Intent;
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
import android.widget.EditText;
import android.widget.RadioButton;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SignUp extends AppCompatActivity {
    public static MediaPlayer music;
    @Override
    //Sign Up Page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set up Drop down menu
        setContentView(R.layout.signup_design);
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[] {"Africa", "China", "India","US & Canada","UK","Australia","Europe","Latin America", "Middle East"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,items);
        dynamicSpinner.setAdapter(adapter);
        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item",(String) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Button Command to move to the next page
        Button btnSimple = (Button) findViewById(R.id.Btn2);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sets up spinner and gets the values selected
                Spinner Spin = (Spinner) findViewById(R.id.spinner);
                String Region = Spin.getSelectedItem().toString();
                Region.replaceAll("\\s+", "").toLowerCase();
                EditText NameInput = (EditText) findViewById(R.id.NameBox);
                String Name = NameInput.getText().toString();
                EditText PasswordInput = (EditText) findViewById(R.id.PasswordBox);
                String Password = PasswordInput.getText().toString();
                //Gets the value fromt he radio button
                RadioButton rb;
                rb = (RadioButton) findViewById(R.id.Male);
                String Sex;
                if (rb.isChecked()) {
                    Sex = "Male";
                } else {
                    Sex = "Female";
                }
                //Checks if the input is acceptable
                int Check_N;
                Check_N = 0;
                if (Name.indexOf(" ") == -1 || Name.equals("Name")) {
                    Check_N = 1;
                }
                String[] res;
                res = Name.split(" ");
                Name = res[0] + res[1];
                int Check_P;
                Check_P = 0;
                if (Password.indexOf(" ") > 0 || Password.equals("")) {
                    Check_P = 1;
                }
                int Check_E;
                Check_E = 0;

                EditText EmailInput = (EditText) findViewById(R.id.EmailBox);
                String Emails = EmailInput.getText().toString();
                if (Emails.indexOf("@") == -1 || Emails.indexOf(".") == -1 || Emails.indexOf("c") == -1 || Emails.indexOf("o") == -1 || Emails.equals("")) {
                    Check_E = 1;
                }
                if (Check_N == 1 || Check_P == 1 || Sex.equals("") || Check_E == 1) {

                    if (Check_N == 1) {
                        Log.e("missing Input", "Name");
                    }
                    if (Check_P == 1) {
                        Log.e("missing Input", "Password");
                    }
                    if (Check_E == 1) {
                        Log.e("missing Input", "Email");
                    }

                } else {
                    //If the input is acceptable then sends the information forward to next page
                    Intent intent = new Intent(v.getContext(), Location_2.class);
                    intent.putExtra("Name", Name);
                    intent.putExtra("Password", Password);
                    intent.putExtra("Email", Emails);
                    intent.putExtra("Region", Region);
                    startActivityForResult(intent, 0);
                }

            }
        });
    }
    /*

    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
    //Repeated from previous page Sets the music on or off.
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
