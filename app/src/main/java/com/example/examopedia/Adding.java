package com.example.examopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Adding extends AppCompatActivity {

    EditText stream,exams,about,date,fees;
    String str,exa,abo,dat,fee;
    Button submit;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        stream= (EditText) findViewById(R.id.et1);
        exams= (EditText) findViewById(R.id.et2);
        date= (EditText)findViewById(R.id.et3);
        about= (EditText) findViewById(R.id.et4);
        fees= (EditText) findViewById(R.id.et5);
        submit= (Button) findViewById(R.id.button);
        database=new Database(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=stream.getText().toString();
                exa = exams.getText().toString();
                abo=about.getText().toString();
                dat=date.getText().toString();
                fee=fees.getText().toString();
                long id =database.adddata(str,exa,abo,fee,dat);
                if(id>0)
                    Toast.makeText(Adding.this,"Added",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Adding.this,"Not Added",Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adding, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
