package com.slinksoft.weeklypubsub;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {

    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 5;
    boolean notified = false;



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);

        startTimer();


        // Create an explicit intent for an Activity in your app


        return START_STICKY;
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");


    }



    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
        //timer.schedule(timerTask, 5000,1000); //
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {

                        //TODO CALL NOTIFICATION FUNC


                        Calendar currentDay = Calendar.getInstance();

                        // debugging purposes only; courtesy of Shrek
                        /*
                        System.out.println("HEY NOW" + currentDay.get(Calendar.DAY_OF_WEEK));
                        System.out.println("YOURE A ROCKSTAR" + currentDay.get(Calendar.HOUR_OF_DAY));
                        System.out.println("GET UR GAME ON OH WAY" + currentDay.get(Calendar.AM_PM));
                         */

                        // Only push the notification on Thursday at 12PM, a common lunch time for customers
                        if (notified == false)
                        {
                            if (currentDay.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                            {
                                if (currentDay.get(Calendar.AM_PM) == Calendar.PM)
                                {
                                    if (currentDay.get((Calendar.HOUR_OF_DAY)) == 12)
                                    {
                                       pushNotification();
                                        notified = true;
                                    }
                                }
                            }
                        }

                        // reset notification after an hour
                        if (notified == true)
                        {
                            if (currentDay.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
                            {
                                if (currentDay.get(Calendar.AM_PM) == Calendar.PM)
                                {
                                    if (currentDay.get((Calendar.HOUR_OF_DAY)) == 13)
                                    {
                                        notified = false;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        };
    }


    private void pushNotification()
    {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Pub_Sub_Channel")
                .setSmallIcon(R.drawable.p)
                .setContentTitle("New Sub Of The Week Is Here!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("You know what day it is: Thursday! The new sub on sale of the week is finally here. Tap to find out what it is! \uD83D\uDC40 \uD83D\uDC40 \uD83D\uDC40"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());


        notificationManager.notify(0, builder.build());
    }
}