package com.examopedia.beta;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.examopedia.beta.JSON.AsyncJSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dilpreet on 9/8/15.
 */
public class FetchAllData {

    Context context;
    int count;
    AsyncJSON asyncJSON;
    public FetchAllData(Context context) {
        super();
        this.context=context;
        count=0;
        asyncJSON=new AsyncJSON(context);

    }

    public void getDataFromServer() {
        RequestQueue queue = Volley.newRequestQueue(context);
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
                            Toast.makeText(context, "Please Wait..", Toast.LENGTH_SHORT).show();
                            count++;
                        }
                        else{

                            MainActivity.progressDialog.cancel();
                            Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
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
                headers.put("If-None-Match", MainActivity.etag);
                Log.d(MainActivity.TAG, "ETAG header in request value:" + MainActivity.etag);
                return headers;

            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Log.d(MainActivity.TAG, "Catching Header value"+response.headers.get("ETAG"));
                MainActivity.etag = response.headers.get("ETAG");
                int mStatusCode = response.statusCode;

                Log.d("Status Response", ":"+mStatusCode);

                SharedPreferences settings = context.getSharedPreferences(MainActivity.PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                if(MainActivity.etag=="") {
                    editor.putString("etagVal", MainActivity.etag);


                    Log.d(MainActivity.TAG, "This will be accessed only first time to set etag"+"etag:"+MainActivity.etag);
                    // Commit the edits!
                    editor.commit();
                }

                Log.d(MainActivity.TAG,"ETAG:"+MainActivity.etag);
                return super.parseNetworkResponse(response);
            }
        };
        queue.add(stringRequest);
        Log.d(MainActivity.TAG, "stringRequestadded in queue");
    }
}
