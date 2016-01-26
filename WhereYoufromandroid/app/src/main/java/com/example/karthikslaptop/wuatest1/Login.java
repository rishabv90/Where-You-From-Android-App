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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;
// Login Page
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_design);
        //Button command for moving to the next page
        Button btnSimple = (Button) findViewById(R.id.btnLogin);
        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gets the values from the text boxes
                EditText NameInput = (EditText) findViewById(R.id.NameBox);
                String Name = NameInput.getText().toString();
                EditText PasswordInput = (EditText) findViewById(R.id.PasswordBox);
                String Password = PasswordInput.getText().toString();

                //Check conditions for username ans password
                int Check_N;
                Check_N = 0;
                if (Name.equals("") || Name.equals("Name")) {
                    Check_N = 1;
                }
                else
                {
                    String[] res;
                    res = Name.split(" ");
                    Name = res[0] + res[1];
                }

                int Check_P;
                Check_P = 0;
                if (Password.indexOf(" ") > 0 || Password.equals("")) {
                    Check_P = 1;
                }


                //Checks if both username and password are acceptable and checks the server if the pair exists
                String result;
                String Link = "http://128.46.69.133/~vipuser3/Login.php?username=" + Name + "+&password=" + Password;
                if (Check_N == 1 || Check_P == 1) {

                    if (Check_N == 1) {
                        Log.e("missing Input", "Name");
                    }
                    if (Check_P == 1) {
                        Log.e("missing Input", "Password");
                    }



                    Log.e("missing Input", "Missing Input");
                } else {
                    enableStrictMode();
                    try {
                            //Calls and executes the file specified by link
                            //Code from http://www.programcreek.com/java-api-examples/org.apache.http.client.methods.HttpGet
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
                            //Closes connection and parses response
                            in.close();
                            result = str.toString();
                            String[] Sr = result.split(":");
                            String Ti[] = Sr[1].split(Pattern.quote("}"));

                            //If the response is a 1 then move forward.
                            if(Ti[0].equals("1"))
                            {
                                Intent intent = new Intent(v.getContext(), PeopleInterest.class);
                                intent.putExtra("Name", Name);
                                startActivityForResult(intent, 0);
                            }
                            //Else respond and wait till an acceptable response is sent in
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();

                            }
                        //In case connection fails.
                    } catch (Exception e) {
                        Log.e("log_tag", "Error in http connection " + e.toString());
                    }

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
