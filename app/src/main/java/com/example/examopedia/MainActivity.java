package com.example.examopedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examopedia.Adapters.ExpandableListAdapter;
import com.example.examopedia.JSON.AsyncJSON;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME="etag";
    private static final String TAG ="MainActivity/Request";
    Database database;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String, List<String>> childData = null;
    List<String> parentData;
    AsyncJSON asyncJSON;
    String etag="";
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        database = new Database(this);
        database.resetdata();

        //Checking whether it is connected to internet or not .
        if(connectivityInfo())
            getDataFromServer();
        else
            Toast.makeText(this,"Please connect to Internet..",Toast.LENGTH_SHORT).show();


        asyncJSON = new AsyncJSON(this);
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


    }

    //Function for checking whether it is connected to internet or not.
    private boolean connectivityInfo(){
        boolean bool=false;
        ConnectivityManager manager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        try{
            NetworkInfo info=manager.getActiveNetworkInfo();
            bool=info.isConnected();
        }
        catch (NullPointerException e){
            //It was throughing a NullPointerException and crash if not connected to internet.
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


    public void getDataFromServer() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String urlstr = "https://intense-brook-1791.herokuapp.com/exams.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncJSON.execute(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Initially count will be zero so if any error occurs
                        //It will make recursive calls provided count is less than five .
                        //Else it will display the else part toast.
                        if(count<5) {
                            getDataFromServer();
                            Toast.makeText(MainActivity.this, "Please Wait..", Toast.LENGTH_SHORT).show();
                            count++;
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
            //some linshttp://stackoverflow.com/questions/28696899/add-custom-headers-with-volley-library
            //
// http://stackoverflow.com/questions/22948006/http-status-code-in-android-volley-when-error-networkresponse-is-null
//http://stackoverflow.com/questions/22272348/volley-cache-using-etag?rq=1
//http://stackoverflow.com/questions/22137374/how-to-return-response-header-field-to-main-method-using-google-volley-for-http
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("If-None-Match", etag);
                Log.d(TAG, "ETAG header in request value:" + etag);
                return headers;

            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.d(TAG, "Catching Header value"+response.headers.get("ETAG"));
                etag = response.headers.get("ETAG");
                int mStatusCode = response.statusCode;

                Log.d("Status Response", ":"+mStatusCode);

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                if(etag=="") {
                    editor.putString("etagVal", etag);


                    Log.d(TAG, "This will be accessed only first time to set etag"+"etag:"+etag);
                    // Commit the edits!
                    editor.commit();
                }

                Log.d(TAG,"ETAG:"+etag);
                return super.parseNetworkResponse(response);
            }
        };
        queue.add(stringRequest);
        Log.d(TAG, "stringRequestadded in queue");
    }
}
