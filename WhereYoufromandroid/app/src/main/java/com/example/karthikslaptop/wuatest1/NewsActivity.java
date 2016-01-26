package com.example.karthikslaptop.wuatest1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsActivity extends ListActivity implements OnItemClickListener {

    //vector that saves the title
    Vector<String> newstitlevector;
    //vector that saves description
    static Vector<String> newsdescriptionvector;
    //url address variable
    String Region;

    String uril ="http://feeds.bbci.co.uk/news/rss.xml?edition=int";
    //declare to connect into the website
    URL newurl;
    String Linked = uril;
    String tagName="",title="",description="";
    static int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String Name = getIntent().getStringExtra("Name");
        String Link = "http://128.46.69.133/~vipuser3/GetRegion.php?Name=" + Name;
        //Gets Region
        enableStrictMode();
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
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder str = new StringBuilder();
            String strline = null;

            while ((strline = in.readLine()) != null) {
                str.append(strline);

            }
            in.close();
            String result = str.toString();
            //Parses response
            Region = result;
            Log.e("REGION", Region);

            Region= Region.replace('"', ' ');
            Region = Region.replaceAll("\\s+","");
        }catch (Exception e){
            Log.e(" ","OOOOOO");
        }
        Log.e("REGION", Region);
        //Populates list
        setListAdapter(new ArrayAdapter<String>(this, R.layout.listitem, getData(Linked)));
        //Create list view
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(this);


    }

    //arg2 saves which section that was clicked at the listview
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        index=arg2;
        //Connect the class with the other class that shows in the screen
        Intent intent = new Intent().setClass(NewsActivity.this, Detail.class);
        startActivity(intent);
    }

    //Call the method to save the title and the description
    public Vector getData(String Uri){

        //Create a vector
        newstitlevector = new Vector<String>();
        newsdescriptionvector =new Vector<String>();
        newstitlevector.add("News from "+Region);
        newsdescriptionvector.add("It's just a title");
        //Gets Titles
        String Name = getIntent().getStringExtra("Name");
        String Link = "http://128.46.69.133/~vipuser3/GetTitles.php?username=" + Name;
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
            String result = str.toString();
            result = result.replace("\\", "");
            result = result.replace('[', ' ');
            result = result.replace(']', ' ');
            New = result.split(",");
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        Link = "http://128.46.69.133/~vipuser3/GetDesc.php?username=" + Name;
        enableStrictMode();
        String[] Desc = new String[] {};
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
            String result = str.toString();
            result = result.replace('"',' ');
            result = result.replace("\\", "");
            result = result.replace('[',' ');
            result = result.replace(']', ' ');
            Desc = result.split(" , ");
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        int Length = New.length;
        int i = 0;
        for(i=0;i < Length;i++)
        {
            newstitlevector.add(New[i]);
        }
        Length = Desc.length;
        for(i=0;i < Length;i++)
        {
            newsdescriptionvector.add(Desc[i]);
        }
        return newstitlevector;
    }
    //Changes code to allow Javascript to recognize certain errors and recognize that the Connection is made correctly
    //Adapted from code found at http://developer.android.com/reference/android/os/StrictMode.ThreadPolicy.Builder.html
    public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }



}






