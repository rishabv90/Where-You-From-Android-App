package com.example.karthikslaptop.wuatest1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//People from your area
public class People extends Activity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_design);
        //Set up List view to be used
        listView = (ListView)findViewById(R.id.list);
        String Name = getIntent().getStringExtra("Name");
        //Connect to Server
        String Link = "http://128.46.69.133/~vipuser3/PeopleList.php?username=" + Name;
        enableStrictMode();
        String[] New = new String[] {};
        try {
            //Sets up request from app
            //Source:http://www.programcreek.com/java-api-examples/org.apache.http.client.methods.HttpGet
            URL url = new URL(Link);
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(Link));
            HttpResponse response = httpclient.execute(request);
            //Receives the response from the request and formats it into a readable format
            //Code from http://stackoverflow.com/questions/10809731/httpresponse-and-bufferedreader
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            BufferedReader in = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder str = new StringBuilder();
            String strline = null;

            while((strline = in.readLine()) != null) {
                str.append(strline);

            }
            in.close();

            //Parse and split result into an list
            String result = str.toString();
            result = result.replace('"',' ');
            result = result.replace('[',' ');
            result = result.replace(']',' ');
            result = result.replace(" ","");
            New = result.split(",");
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }


        //Populates the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, New);

        listView.setAdapter(adapter);
        //On click just shows the element
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int ItemPosition = position;

                String itemValue = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),
                        "Position : " + ItemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main5, menu);
        return true;
    }
    //Changes code to allow Javascript to recognize certain errors and recognize that the Connection is made correctly
    //Adapted from code found at http://developer.android.com/reference/android/os/StrictMode.ThreadPolicy.Builder.html
    public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
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
