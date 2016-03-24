package com.example.pavan.redtweet;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class SignupActivity extends Activity {

    int flag = 0;
    String myname;
    String myemail;
    String mypass;
    String myph;
    String bldgrp;
    Boolean mybool;
    String myloc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_signup);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText name = (EditText) findViewById(R.id.input_name);
        final EditText email = (EditText) findViewById(R.id.input_email);
        final EditText password = (EditText) findViewById(R.id.input_password);
        final EditText phone = (EditText) findViewById(R.id.input_phone);
        final Spinner bgrp = (Spinner) findViewById(R.id.Spinner01);
        final EditText location = (EditText) findViewById(R.id.location);
        Button submit = (Button) findViewById(R.id.butt);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myname = name.getText().toString();
                if (myname.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                myemail = email.getText().toString();
                if (myemail.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                mypass = password.getText().toString();
                if (mypass.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a password", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                myph = phone.getText().toString();
                if (myph.matches("") || myph.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                bldgrp = bgrp.getSelectedItem().toString();
                myloc = location.getText().toString();
                if (myloc.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your location", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                if (flag == 0) {
                    EntryDB edb = new EntryDB();
                    edb.execute();
                }
            }
        });
    }

    public class EntryDB extends AsyncTask<Void, Void, Boolean> {

        String myresponse;


        @Override
        protected Boolean doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            Uri.Builder build = new Uri.Builder();
            //String build = "http://192.168.0.104/1.php";
            build.scheme("http").authority("192.168.0.104").appendPath("2.php").appendQueryParameter("name",myname)
                    .appendQueryParameter("email",myemail).appendQueryParameter("pass",mypass)
                    .appendQueryParameter("phone",myph).appendQueryParameter("myblood",bldgrp)
                    .appendQueryParameter("location",myloc);
            Log.e("the url ", build.toString());
            try {
                URL url = new URL(build.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                //urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    myresponse = null;
                }
                Log.e("here", "reached");
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    myresponse = null;
                }
                myresponse = buffer.toString();
                //Log.e("the fin", myresponse+" "+myresponse.getClass().getName());
                Log.e("ha", String.valueOf(myresponse.contains("success")));
                return myresponse.contains("success");
            } catch (Exception e) {
                Log.e("error", e.getMessage());

            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean myb){
            if(myb){
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
            }
        }


    }
}
