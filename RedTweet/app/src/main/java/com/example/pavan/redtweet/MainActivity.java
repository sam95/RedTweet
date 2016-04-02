package com.example.pavan.redtweet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends Activity {
    //private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!checkPlayServices()) {
                       // This is where we could either prompt a user that they should install
                                // the latest version of Google Play Services, or add an error snackbar
                                        // that some features won't be available.
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean sentToken = sharedPreferences.getBoolean(SENT_TOKEN_TO_SERVER, false);
            if (!sentToken) {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }


    private boolean checkPlayServices() {
                GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
                int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
                if (resultCode != ConnectionResult.SUCCESS) {
                        if (apiAvailability.isUserResolvableError(resultCode)) {
                                apiAvailability.getErrorDialog(this, resultCode,
                                                PLAY_SERVICES_RESOLUTION_REQUEST).show();
                            } else {
                                Log.i(LOG_TAG, "This device is not supported.");
                                finish();
                            }
                        return false;
                    }
                return true;
            }


}
