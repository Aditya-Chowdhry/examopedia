package com.examopedia.beta.NotificationService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
