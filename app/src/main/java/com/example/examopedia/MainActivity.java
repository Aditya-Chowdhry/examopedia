package com.example.examopedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Database database;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    HashMap<String,List<String>> childData=null;
    List<String> parentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);
        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
       provideData();
        expandableListAdapter=new ExpandableListAdapter(this,parentData,childData);
        expandableListView.setAdapter(expandableListAdapter);

        /*expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String nameOfExam=childData.get(parentData.get(groupPosition)).get(childPosition);
                //database.changecursorposition(nameOfExam);
                String about = database.getAbout();
                String date= database.getDate();
                String fees = database.getFees();
                Intent Intent = new Intent(MainActivity.this, Details.class);
                Intent.putExtra("about",about);
                Intent.putExtra("date",date);
                Intent.putExtra("fees",fees);
                startActivity(Intent);

                return false;
            }
        });*/


    }



   public void provideData(){
        parentData=new ArrayList<>();
        childData=new HashMap<>();
       ArrayList<String> ar=new ArrayList<>();
       ar.add("");
        parentData.add("Arts");
        childData.put("Arts", ar);

        parentData.add("Engineering");
       childData.put("Engineering", ar);
        //childData.put("Engineering",database.displaylist("Engineering"));

       parentData.add("Medical");
       childData.put("Medical", ar);

    }

    @Override
    protected void onResume() {
        super.onResume();
       // provideData();
        expandableListAdapter=new ExpandableListAdapter(this,parentData,childData);
        expandableListView.setAdapter(expandableListAdapter);
    }



    public void display(View view){
        startActivity(new Intent(getApplicationContext(),Demo.class));
    }




}
