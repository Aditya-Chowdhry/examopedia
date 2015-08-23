package com.example.examopedia.NotificationService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public MyService() {

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new GetNotifications(this);
        stopSelf();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

}
