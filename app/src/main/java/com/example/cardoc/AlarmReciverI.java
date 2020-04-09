package com.example.cardoc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;

public class AlarmReciverI extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        String message = intent.getStringExtra("atodo");

        NotificationHelper notificationHelper=new NotificationHelper(context);
        NotificationCompat.Builder nb=notificationHelper.getChannel1Notification("Insurance Expired!",message);
        notificationHelper.getManager().notify(2,nb.build());

    }
}
