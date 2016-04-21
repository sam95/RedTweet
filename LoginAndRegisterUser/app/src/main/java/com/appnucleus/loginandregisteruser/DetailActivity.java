package com.appnucleus.loginandregisteruser;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import helper.SQLiteHandler;

public class DetailActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtphone;
    private TextView txtblood;
    private TextView txtcity;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detail);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtphone = (TextView) findViewById(R.id.phone);
        txtblood = (TextView) findViewById(R.id.b_group);
        txtcity = (TextView) findViewById(R.id.city);


        db = new SQLiteHandler(getApplicationContext());
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String sharedemail=pref.getString("key_name5", null);
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails(sharedemail);
        Log.e("haha",sharedemail);
        String name = user.get("name");
        String email = user.get("email");
        String phone = user.get("phone");
        String b_group = user.get("b_group");
        String location = user.get("location");

        txtName.setText(name);
        txtEmail.setText(email);
        txtphone.setText(phone);
        txtblood.setText(b_group);
        txtcity.setText(location);
    }

}
