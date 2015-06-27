package com.example.examopedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    Database database;
    long id;
    ArrayList<String> ar=new ArrayList<>();
    String[] days= {"Engineering","Medical"};
    ListView primary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);
        /*id =database.adddata("Engineering","Jee mains","For addmision in IIT","1100","4-04-2016");
        if (id>0)
            Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Not Added",Toast.LENGTH_SHORT).show();*/

///////////////////HEREREEEEEEEEEEEEEEEEEEEEEEE/////////////////
        primary= (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,days);
        primary.setAdapter(adapter);
        primary.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void adding(){
        Intent adds=new Intent(this,Adding.class);
        startActivity(adds);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.add){
            adding();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(this,Second.class);
        i.putExtra("category",position);
        startActivity(i);
    }

}
