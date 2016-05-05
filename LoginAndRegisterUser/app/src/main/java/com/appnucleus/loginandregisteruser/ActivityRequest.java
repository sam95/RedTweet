package com.appnucleus.loginandregisteruser;




import android.content.Intent;
        import android.os.StrictMode;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpHost;
        import org.apache.http.HttpRequest;
        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.ResponseHandler;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.client.methods.HttpUriRequest;
        import org.apache.http.conn.ClientConnectionManager;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.params.HttpParams;
        import org.apache.http.protocol.HttpContext;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Code to demonstrate the insertion of data into android application.
 * Also demonstrates how to retrieve the data.
 * Uses the localhost for local server.
 * Php scripting language for backend integration.
 */

public class ActivityRequest extends ActionBarActivity {

    EditText etcity, etunits, etb_group, ethospital, etcontact;
    private Spinner inputb_group;
    Button bSubmit;
    public static String url = "http://188.166.217.124/workshop_connect.php";
    String city, units, b_group, hospital, contact;
    InputStream is = null;
    String exceptionMessage = "There seems to be some problem connecting to database. " +
            "Please check your Internet Connection and try again.";
    String successMessage = "Blood request successfully posted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity_request);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        etcity = (EditText) findViewById(R.id.city);
        etunits = (EditText) findViewById(R.id.units);
       // etb_group = (EditText) findViewById(R.id.b_group);
        inputb_group = (Spinner) findViewById(R.id.b_group);
        ethospital = (EditText) findViewById(R.id.hospital);
        etcontact = (EditText) findViewById(R.id.contact);
        bSubmit = (Button) findViewById(R.id.button_submit);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city = etcity.getText().toString();
                units = etunits.getText().toString();
               // b_group = etb_group.getText().toString();
                b_group = inputb_group.getSelectedItem().toString();
                hospital = ethospital.getText().toString();
                contact = etcontact.getText().toString();
                if(city.equals("") ||
                        units.equals("") ||
                        b_group.equals("")){
                    String msg = "One or more fields are empty";
                    etcity.setText("");
                    etunits.setText("");
                   // etb_group.setText("");
                 //   inputb_group.setText("");
                    ethospital.setText("");
                    etcontact.setText("");
                }
                else{

                    List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                    nameValuePairList.add(new BasicNameValuePair("city", city));
                    nameValuePairList.add(new BasicNameValuePair("units", units));
                    nameValuePairList.add(new BasicNameValuePair("b_group", b_group));
                    nameValuePairList.add(new BasicNameValuePair("hospital", hospital));
                    nameValuePairList.add(new BasicNameValuePair("contact", contact));

                    try{
                        URL url1 = new URL(url);
                        HttpURLConnection urlConnection;// = (HttpURLConnection) url.openConnection();;
                        urlConnection = (HttpURLConnection) url1.openConnection();
                        urlConnection.setRequestMethod("GET");
                        urlConnection.connect();
                        is = urlConnection.getInputStream();
                        etcity.setText("");
                        etunits.setText("");
                       // etb_group.setText("");
                        ethospital.setText("");
                        etcontact.setText("");
                        Toast.makeText(getApplicationContext(), successMessage, Toast.LENGTH_SHORT).show();
                        is.close();
                    }catch(IOException e){
                        Toast.makeText(getApplicationContext(), exceptionMessage, Toast.LENGTH_SHORT).show();
                    }
                }
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

        else if( id == R.id.action_retrieve_data){
            Intent it = new Intent(ActivityRequest.this, Activity_Main.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }
}