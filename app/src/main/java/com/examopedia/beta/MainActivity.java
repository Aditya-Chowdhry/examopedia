package com.examopedia.beta;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.examopedia.beta.Adapters.ExpandableListAdapter;
import com.examopedia.beta.Articles.ArticlesActivity;
import com.examopedia.beta.NotificationActivity.NotificationCentre;
import com.examopedia.beta.NotificationService.MyReceiver;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*----------------Mobile analytics by amazaon--------------------*/
import com.amazonaws.mobileconnectors.amazonmobileanalytics.*;

/*---------------------------------------------------------------*/
public class MainActivity extends AppCompatActivity {
    /*----------------Mobile analytics by amazaon--------------------*/
    private static MobileAnalyticsManager analytics;

    /*--------------------------------------------------------------*/

    private static final String PREFS_NAME2 ="register" ;
    File f;
    public static final String PREFS_NAME="etag";
    public static final String TAG ="MainActivity/Request";
    Database database;
    EditText name;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, List<String>> childData = null;
    List<String> parentData;

    public static PendingIntent pendingIntent;
    public static AlarmManager alarmManager;

    public static String etag="";
    public static ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name= (EditText) findViewById(R.id.editText8);
        sharedPreferences=getSharedPreferences("alarm",MODE_PRIVATE);
        database = new Database(this);

        ///For the alarm manger///

        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        int result=sharedPreferences.getInt("alarmKey",1);

        Intent intent=new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

        if(result==1)
            startReciver();
        //End of alarm manger//

        /*----------------Mobile analytics by amazaon--------------------*/
        try {
            analytics = MobileAnalyticsManager.getOrCreateInstance(
                    this.getApplicationContext(),
                    "962b17fbe75b4d8bbcd517166cdf54e9", //Amazon Mobile Analytics App ID
                    "us-east-1:fc784da0-6fe4-4c9c-ae92-7f19cba11ab0" //Amazon Cognito Identity Pool ID
            );
        } catch(InitializationException ex) {
            Log.e(this.getClass().getName(), "Failed to initialize Amazon Mobile Analytics", ex);
        }
        /*--------------------------------------------------------------*/


        /*--------------------------------------User Form Area + First Request To server for data----------------------------------------*/

        //Checking whether the its the first time for the form
        SharedPreferences settings2=getSharedPreferences(PREFS_NAME2,Context.MODE_PRIVATE);
        SharedPreferences.Editor userEditor=settings2.edit();
        if(settings2.getBoolean(PREFS_NAME2, true)){

            //UserInfo Dialog Is created
              userInfoDialog();





        }
        userEditor.putBoolean(PREFS_NAME2, false);
        userEditor.commit();

        /*--------------------------------------End of User Form Area + First Request To server for data--------------------------*/


        /*----------------------------------------Etag Caching--------------------------------------------*/
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);//http://developer.android.com/guide/topics/data/data-storage.html
        //This used for the purpose of stroing ETAG value
        //Whenever user press the refresh button
        //The value of this header will be sent along with get request
        String etagVal=settings.getString("etagVal",""); //initially null;
//        Retrieve a String value from the preferences.
//        Parameters
//        key	The name of the preference to retrieve.
//        defValue	Value to return if this preference does not exist.
        etag=etagVal;

        /*----------------------------------------End of Etag Caching---------------------------------------*/


/*
        SharedPreferences sp=getSharedPreferences("first_Startup",Context.MODE_PRIVATE);  //checking for first run of the app
        SharedPreferences.Editor editor=sp.edit();
        if(sp.getBoolean("firstTime",true)){
            userData();
        }
        editor.putBoolean("firstTime",false).commit();
*/


        /*-----------------------------------Expandable List-----------------------------------------*/

       expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        provideData();
        expandableListAdapter = new ExpandableListAdapter(this, parentData, childData);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String level = childData.get(parentData.get(groupPosition)).get(childPosition);
                String section = parentData.get(groupPosition);
                ArrayList<String> list = new ArrayList<String>();
                list = database.populateList(section, level);
                Intent intent = new Intent(MainActivity.this, ExamTitles.class);
                intent.putStringArrayListExtra("list", list);
                startActivity(intent);

                return false;
            }
        });

        /*-----------------------------------End of Expandable List-----------------------------------------*/



    }
     /*----------------Mobile analytics by amazaon--------------------*/

    @Override
    protected void onPause() {
        super.onPause();
        if(analytics != null) {
            analytics.getSessionClient().pauseSession();
            analytics.getEventClient().submitEvents();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(analytics != null) {
            analytics.getSessionClient().resumeSession();
        }
    }

     /*----------------------------------------------------------*/



    //Function for checking whether it is connected to internet or not.
    private boolean connectivityInfo(){
        boolean bool=false;
        ConnectivityManager manager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        try{
            NetworkInfo info=manager.getActiveNetworkInfo();
            bool=info.isConnected();
        }
        catch (NullPointerException e){
            //It was throwing a NullPointerException and crash if not connected to internet.
            //Working fine with this.
        }


        return bool;
    }



    public void provideData() {
        parentData = new ArrayList<>();
        childData = new HashMap<>();
        ArrayList<String> ar = new ArrayList<>();
        ar.add("undergraduate");
        ar.add("postgraduate");

        parentData.add("Arts");
        childData.put("Arts", ar);


        parentData.add("Commerce");
        childData.put("Commerce", ar);

        parentData.add("Science");
        childData.put("Science", ar);
    }



    public void userData(){
        startActivity(new Intent(MainActivity.this, UserDetails.class));
    }

    public void userInfoDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setView(R.layout.dialog_design);
        dialog.setTitle("User Details");
        dialog.setCancelable(false);

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
                // first Request
                database.resetdata();

                //Checking whether it is connected to internet or not .
                if (connectivityInfo()) {

                    //Creating a loading dialog
                    loadingDialog();


                    //Initializing FetchAllData to send Volley String request
                    FetchAllData fetchAllData = new FetchAllData(MainActivity.this);

                    //sending request to server
                    fetchAllData.getDataFromServer();
                } else
                    Toast.makeText(MainActivity.this, "Please connect to Internet..", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();

    }

    public void loadingDialog(){
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait.While we load your exams!\nMake sure you have a working internet connection.");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }



    //
    public void getFeed(View view){

        this.startActivity(new Intent(this, ArticlesActivity.class));

    }
    //




    public void load_exam(View view){
        if (connectivityInfo()) {

            //Creating a loading dialog
            loadingDialog();


            //Initializing FetchAllData to send Volley String request
            FetchAllData fetchAllData = new FetchAllData(MainActivity.this);

            //sending request to server
            fetchAllData.getDataFromServer();
        } else
            Toast.makeText(MainActivity.this, "Please connect to Internet..", Toast.LENGTH_SHORT).show();


    }
    //Alarm manager for starting a triggering a broadcast reciever in a regular interval which will start the service .

    public static void startReciver(){


        //Calendar calendar=Calendar.getInstance();
        long time=System.currentTimeMillis();
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, time, 3000, pendingIntent);
    }

    public void startNot(View view){
        this.startActivity(new Intent(this, NotificationCentre.class));
    }

    public static void cancelAlarm(){
        alarmManager.cancel(pendingIntent);
    }

    public void setting(View view){
        startActivity(new Intent(this, Settings.class));
    }
}
