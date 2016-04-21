package com.appnucleus.loginandregisteruser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {


    private Button btnDetail;
    private Button btnFill;
    private Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);

        btnDetail = (Button) findViewById(R.id.mydetails);
        btnFill = (Button) findViewById(R.id.donationdetail);
        btnHistory = (Button) findViewById(R.id.donationhistory);


        btnDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                detail();
            }
        });
       /*btnFill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fill();
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                history();
            }
        });*/

    }

    private void detail(){

        Intent i = new Intent(ProfileActivity.this, DetailActivity.class);
        // i.putExtra("KEY_B_GROUP",b_group);
        //i.putExtra("KEY_LOCATION",location);
        startActivity(i);



    }
    private void fill(){

        Intent i = new Intent(ProfileActivity.this, FillActivity.class);
        // i.putExtra("KEY_B_GROUP",b_group);
        //i.putExtra("KEY_LOCATION",location);
        startActivity(i);



    }
    private void history(){

        Intent i = new Intent(ProfileActivity.this, HistoryActivity.class);
        // i.putExtra("KEY_B_GROUP",b_group);
        //i.putExtra("KEY_LOCATION",location);
        startActivity(i);



    }

}
