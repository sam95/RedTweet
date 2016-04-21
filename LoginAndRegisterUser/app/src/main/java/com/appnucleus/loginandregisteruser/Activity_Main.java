package com.appnucleus.loginandregisteruser;

import helper.SQLiteHandler;
import helper.SessionManager;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Main extends Activity {

    private TextView txtName;
    private TextView txtEmail;
   // private TextView txtb_group;
   // private TextView txtloc;
    private Button btnLogout;
    private Button btnRetrieve;
    private Button btnRequest;
    private Button btnProfile;
    private SQLiteHandler db;
    private SessionManager session;
    String name;
    String email;
    String b_group;
    String location;
    String finalb_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
      //  txtb_group = (TextView) findViewById(R.id.b_group);
      //  txtloc = (TextView) findViewById(R.id.location);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnRetrieve = (Button) findViewById(R.id.retrieve);
        btnRequest = (Button) findViewById(R.id.request);
        btnProfile = (Button) findViewById(R.id.profile);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String sharedemail=pref.getString("key_name5", null);
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails(sharedemail);

        name = user.get("name");
        email = user.get("email");
        b_group = user.get("b_group");
        location = user.get("location");
    if(b_group != null && !b_group.equals("")) {

        if (b_group.equals("A+"))
            finalb_group = "a-plus";

        if (b_group.equals("A-"))
            finalb_group = "a-minus";

        if (b_group.equals("B+"))
            finalb_group = "b-plus";

        if (b_group.equals("B-"))
            finalb_group = "b-minus";

        if (b_group.equals("AB+"))
            finalb_group = "ab-plus";

        if (b_group.equals("AB-"))
            finalb_group = "ab-minus";

        if (b_group.equals("O+"))
            finalb_group = "o-plus";

        if (b_group.equals("O-"))
            finalb_group = "o-minus";
    }



        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);
      //  txtb_group.setText(b_group);
      //  txtloc.setText(location);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                retrieve();
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                request();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                profile();
            }
        });





    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        //db.deleteUsers();
        SharedPreferences preferences =getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        // Launching the login activity
        Intent intent = new Intent(Activity_Main.this, Activity_Login.class);
      //  intent.putExtra();
      //  getIntent()
        startActivity(intent);
        finish();
    }

    private void retrieve(){

        Intent i = new Intent(Activity_Main.this, Activity_Retrieve.class);
        i.putExtra("KEY_B_GROUP",finalb_group);
        i.putExtra("KEY_LOCATION", location);
        startActivity(i);



    }

    private void request(){

        Intent i = new Intent(Activity_Main.this, ActivityRequest.class);
       // i.putExtra("KEY_B_GROUP",b_group);
        //i.putExtra("KEY_LOCATION",location);
        startActivity(i);



    }

    private void profile(){

        Intent i = new Intent(Activity_Main.this, ProfileActivity.class);
        // i.putExtra("KEY_B_GROUP",b_group);
        //i.putExtra("KEY_LOCATION",location);
        startActivity(i);



    }
}