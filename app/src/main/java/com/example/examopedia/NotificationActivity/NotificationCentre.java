package com.example.examopedia.NotificationActivity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examopedia.Adapters.NotificationAdapter;
import com.example.examopedia.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotificationCentre extends AppCompatActivity {

    ListView listView;
    ArrayList<Model> list;
    NotificationAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_centre);
        listView=(ListView)findViewById(R.id.listNotify);
        list=new ArrayList<>();
        adapter=new NotificationAdapter(list,this);
        listView.setAdapter(adapter);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait .");
        progressDialog.show();

        getData();

    }

    private void getData(){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url="https://intense-brook-1791.herokuapp.com/notifications.json";
        StringRequest  request=new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("mytag", error.toString());
            }
        });
        queue.add(request);
    }

    private void parseJSON(String json){
        JSONArray array;

        try{
            array =new JSONArray(json);
            JSONObject object;
            for(int i=array.length()-1;i>=0;i--){
                object=array.getJSONObject(i);
                Model newItem=new Model();
                newItem.setTitle(object.getString("title"));
                newItem.setDescription(object.getString("description"));
                newItem.setLink1(object.getString("link1"));
                newItem.setLink2(object.getString("link2"));
                newItem.setLink1_name(object.getString("link1_name"));
                newItem.setLink2_name(object.getString("link2_name"));

                list.add(newItem);

            }
            adapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }
        catch (Exception e){
            Log.d("mytag",e.toString());
        }

    }

}
