package com.appnucleus.loginandregisteruser;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    private static final String EXTRA_TWEET = "tweet";
    private static final String EXTRA_LOCATION = "location";

    public static final int NOTIFICATION_ID = 1;

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onMessageReceived(String from, Bundle data) {
        // Time to unparcel the bundle!
        if (!data.isEmpty()) {
            // TODO: gcm_default sender ID comes from the API console
            String senderId = getString(R.string.gcm_defaultSenderId);
            if (senderId.length() == 0) {
                Toast.makeText(this, "SenderID string needs to be set", Toast.LENGTH_LONG).show();
            }
            Log.e("sender",from+" "+senderId);
            JSONObject json = new JSONObject();
            Set<String> keys = data.keySet();
            for (String key : keys) {
                try {
                    // json.put(key, bundle.get(key)); see edit below
                    json.put(key, JSONObject.wrap(data.get(key)));
                } catch(JSONException e) {
                    //Handle exception here
                }
            }
            Log.e("the value", json.toString());
            // Not a bad idea to check that the message is coming from your server.
            if ((senderId).equals(from)) {
                // Process message and then post a notification of the received message.
                try {
                    String weather = json.getString(EXTRA_TWEET);
                    String location = json.getString(EXTRA_LOCATION);
                    Log.e(weather,location);
                    String alert = weather + location;
                    Log.e(weather, location);
                    sendNotification(alert);
                } catch (Exception e) {
                    // JSON parsing failed, so we just let this message go, since GCM is not one
                    // of our critical features.
                    sendNotification("wassup");
                    Log.e("exception",e.getMessage());
                }
            }
            Log.i(TAG, "Received: " + data.toString());
        }
    }

    /**
     *  Put the message into a notification and post it.
     *  This is just one simple example of what you might choose to do with a GCM message.
     *
     * @param message The alert message to be posted.
     */
    private void sendNotification(String message) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, Activity_Login.class), 0);

        // Notifications using both a large and a small icon (which yours should!) need the large
        // icon as a bitmap. So we need to create that here from the resource ID, and pass the
        // object along in our notification builder. Generally, you want to use the app icon as the
        // small icon, so that users understand what app is triggering this notification.
        //Bitmap largeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.art_storm);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("RedTweet Alert!")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
