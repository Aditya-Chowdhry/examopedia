package com.example.examopedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Second extends AppCompatActivity {

    ArrayList<String> ar = new ArrayList();
    Database database;
    int index;
    String[] nums = { "abc", "def" };
    ListView secondary;
    String[] topics = { "Engineering", "Medical" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        database = new Database(this);
        index = getIntent().getIntExtra("category", 0);
        ar = this.database.displaylist(topics[index]);
        secondary = ((ListView)findViewById(R.id.sec));
        ArrayAdapter localArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.ar);
        secondary.setAdapter(localArrayAdapter);
        secondary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                processing(ar,position);
            }
        });
    }

    private void processing(ArrayList<String> paramArrayList, int paramInt)
    {
        String[] arrayOfString = (String[])paramArrayList.toArray(new String[paramArrayList.size()]);
        database.changecursorposition(arrayOfString[paramInt]);
        String str1 = database.getAbout();
        String str2 = database.getDate();
        String str3 = database.getFees();
        Intent Intent = new Intent(this, Details.class);
        Intent.putExtra("about",str1);
        Intent.putExtra("date",str2);
        Intent.putExtra("fees",str3);
        startActivity(Intent);
    }
}
