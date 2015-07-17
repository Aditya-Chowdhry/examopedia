package com.example.examopedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ExamTitles extends AppCompatActivity {
    Database database;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_titles);
        listView=(ListView)findViewById(R.id.examTitleList);
        database=new Database(this);
        final ArrayList<String> arrayList=getIntent().getStringArrayListExtra("list");
        adapter=new ArrayAdapter<String>(ExamTitles.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.giveExamDetails(arrayList.get(position));
                startActivity(new Intent(ExamTitles.this,SwipeTab.class));
                Toast.makeText(ExamTitles.this,arrayList.get(position)+":: "+Database.title+Database.description+Database.section+Database.level+Database.exam_date,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
