package com.examopedia.beta.NotificationService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       Log.d("mytag","Started reciever");
       context.startService(new Intent(context,MyService.class));
    }
}
