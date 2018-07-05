package in.eloksolutions.divaz.messaging;

/**
 * Created by Vishnu on 9/24/2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import in.eloksolutions.divaz.MainActivity;
import in.eloksolutions.divaz.R;
import in.eloksolutions.divaz.util.Config;
import in.eloksolutions.divaz.util.NotificationUtils;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Bitmap bitmap;
    RemoteViews contentViewBig;
    private NotificationUtils notificationUtils;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            String imageUri= remoteMessage.getData().get("img_path");
            Log.i(TAG, "Message data Image Path: " + imageUri);
            //bitmap = getBitmapfromUrl(imageUri);
            try {
                bitmap= Glide.with(this.getApplicationContext()).load("https://s3.amazonaws.com/divaz/siri/messages/M_338_1529988908019").asBitmap().into(100,100).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "Message data bitmap Path: " + Config.IMG_AWS+imageUri);
            sendNotification(remoteMessage.getData().get("subject"),bitmap,remoteMessage.getData().get("description"));
            Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
            resultIntent.putExtra("message", "1");
            showNotificationMessageWithBigImage(this.getApplicationContext(), imageUri, remoteMessage.getData().get("description"), remoteMessage.getData().get("description"),resultIntent , imageUri);
           // sendNotification("Ayyappa Notification");
            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
               // scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            String imageUri= remoteMessage.getData().get("img_path");
           //bitmap = getBitmapfromUrl(imageUri);
            try {
                bitmap= Glide.with(this.getApplicationContext()).load(Config.IMG_AWS+imageUri).asBitmap().into(100,100).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            Bundle extras = new Bundle();

        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Log.i(TAG, "showNotificationMessageWithBigImage"+imageUrl);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context);

        final Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + context.getPackageName() + "/raw/notification");
        notificationUtils.showBigNotification(bitmap, mBuilder, R.drawable.beatys, title, message, timeStamp, resultPendingIntent, alarmSound);
        showNotification(title);
    }
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */


    private void sendNotification(String messageBody, Bitmap image, String TrueOrFalse) {
        /*Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("AnotherActivity", TrueOrFalse);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Log.i(TAG, "Message data bitmap Path:image " + image);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(image)*//*Notification icon image*//*
                .setSmallIcon(R.drawable.love)
                .setContentTitle(messageBody)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(image))*//*Notification with Image*//*
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationCompat.BigPictureStyle style=new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);
        notificationBuilder.setStyle(style);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/



            //Comment the code where CleverTap renders the notification for you
            //CleverTapAPI.createNotification(getApplicationContext(), extras);
            long when = System.currentTimeMillis();
            NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            contentViewBig = new RemoteViews(getPackageName(), R.layout.customlayout_example);

            //Null and Empty checks for your Key Value Pairs
            if(bitmap!=null) {
                Log.i(TAG,"The notification bit map notification method"+bitmap);

               // contentViewBig.setImageViewBitmap(R.id.image_pic, bitmap);
                contentViewBig.setImageViewResource(R.id.image_pic, R.id.phone);
                Log.i(TAG,"The notification bit map notification method"+bitmap);

            }



            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.id.about_us)

                    .setContentTitle("Custom Notification")
                    .setContentIntent(contentIntent)
                    .setCustomBigContentView(contentViewBig)
                    .setAutoCancel(true)
                    .setWhen(when);

            mNotificationManager.notify(1, notificationBuilder.build());



    }



    private void showNotification(String message) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setAutoCancel(true);
        builder.setContentTitle("Mesaging");
        builder.setCustomBigContentView(contentViewBig);
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_loves);
        builder.setContentIntent(pendingIntent);
        Notification.BigPictureStyle style=new Notification.BigPictureStyle();
        style.bigPicture(bitmap);
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }

    /*
    *To get a Bitmap image from the URL received
    * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}