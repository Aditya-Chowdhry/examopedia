package com.examopedia.beta.NotificationService;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.examopedia.beta.SingletonRequest;
import com.examopedia.beta.NotificationActivity.NotificationCentre;
import com.examopedia.beta.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by dilpreet on 23/8/15.
 */

//Creating  a request queue eerytime will lead to memory exception. Therefore use a singleton class
//http://stackoverflow.com/questions/24021411/volley-newrequestqueue-is-causing-outofmemoryerror

public class GetNotifications {
    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int size;
    String list="";

    public GetNotifications(Context context) {
        super();
        this.context=context;
        preferences=context.getSharedPreferences("size", Context.MODE_PRIVATE);
        editor=preferences.edit();

       // clearList();

        //getting the initial size of the number of notifications
        getListSize();



        //starting asynctask
        new GetData().execute();
    }

    //Class for Getting And processing the data
    private class GetData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {

            RequestQueue queue = SingletonRequest.getInstance(context).
                    getRequestQueue();
            String url="https://intense-brook-1791.herokuapp.com/notifications.json";
            StringRequest request=new StringRequest(url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            parseJSON(response);
                            Log.d("mytag","sent");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
           // request.setShouldCache(false);
            SingletonRequest.getInstance(context).addToRequestQueue(request);
            return null;
        }
    }


    private void getListSize(){
        size=preferences.getInt("array",0);
        Log.d("mytag","inital size :"+size);
    }
    private void clearList(){
        editor.putInt("array",0);
        editor.commit();
    }
    private void saveListSize(int s){
        editor.putInt("array",s);
        editor.commit();
    }


    ///Fucntion for Parsing data
    private void parseJSON(String json){
        JSONArray array;
        int count=0;
        try{
            array =new JSONArray(json);
            count=array.length();

            JSONObject jsonObject;

            for(int i=size;i<count;i++){
                jsonObject=array.getJSONObject(i);
                list=list+jsonObject.getString("title")+"\n";
            }
            Log.d("mytag",count+"");

            //It means new notifications
            if(count>size){
                clearList();
                saveListSize(count);
                displayNotification(count-size);

            }
        }
        catch (Exception e){

        }

    }

    //Display notification
    private void displayNotification(int length){
        Intent in=new Intent(context, NotificationCentre.class);
        PendingIntent pi= PendingIntent.getActivity(context,0,in,0);
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notify=new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.logo)
                .setContentTitle("New Exams are added")
                .setContentInfo(length + " new exams have been added")
                .setContentIntent(pi)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(list))
                .setDefaults(Notification.DEFAULT_ALL);
        manager.notify(11,notify.build());
    }
}
