package com.example.examopedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity {
    Database database;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String,List<String>> childData=null;
    List<String> parentData;
    AsyncJSON asyncJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        database=new Database(this);
        database.resetdata();

        getDataFromServer();
        asyncJSON=new AsyncJSON(this);
        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
       provideData();
        expandableListAdapter=new ExpandableListAdapter(this,parentData,childData);
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



   public void provideData(){
        parentData=new ArrayList<>();
        childData=new HashMap<>();
       ArrayList<String> ar=new ArrayList<>();
       ar.add("undergraduate");
       ar.add("postgraduate");

        parentData.add("Arts");
        childData.put("Arts", ar);



       parentData.add("Commerce");
       childData.put("Commerce", ar);

       parentData.add("Science");
       childData.put("Science", ar);
   }






    public void getDataFromServer(){
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        String urlstr = "https://intense-brook-1791.herokuapp.com/exams.json";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       asyncJSON.execute(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Error "+error,Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);

    }




}
