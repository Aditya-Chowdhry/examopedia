package com.example.examopedia;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Demo extends AppCompatActivity {
    Database database;
    Download downloaddata;
    ListView listView;
    List<String> list=null;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        database = new Database(this);
        listView=(ListView)findViewById(R.id.listView);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        startDownload();





    }

    public void startDownload(){
        Toast.makeText(getApplicationContext(), "Downloading", Toast.LENGTH_LONG).show();
        String urlstr = "https://intense-brook-1791.herokuapp.com/exams.json";

        URL url = null;
        try {
            url = new URL(urlstr);
        } catch (MalformedURLException e) {
            Toast.makeText(getApplicationContext(), "URL Incorrect", Toast.LENGTH_LONG).show();
        }
        //Sending data to Download class for downloading
        downloaddata = new Download();
        downloaddata.execute(url);
    }

    public class Download extends AsyncTask<URL, Void, Void> {


        Integer id;
        String title, description, section, level, image_file_name, image_content_type, image_file_size, image_updated_at, exam_date;
        String form_release_date, form_last_date, link1_name, link1, link2_name, link2, link3_name, link3, link4_name, link4, created_at, updated_at;
        String exam_review, gen_fees_boys, gen_fees_girls, sc_fees_boys, sc_fees_girls, others_note, others;


        @Override
        protected Void doInBackground(URL... params) {
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            StringBuilder builder = null;
            try {
                //MAKING THE CONNECTION
                connection = (HttpURLConnection) params[0].openConnection();
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                connection.connect();
                Log.d("Demo.java", "Connection succeeded");
                //GETTING THE DATA
                inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                builder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }

                Log.d("Data", builder.toString());
                inputStream.close();
                Log.d("demo", "connection closed");

                //PARSING DATA AND STORING IT IN DATABASE
                jsonParsing(builder.toString());

            } catch (IOException e) {
                Log.d("Data", "Error " + e);
            } finally {
                connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void str) {
            super.onPostExecute(str);
            //Parsing Data


            list=database.displaylist();
            adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.child_list,R.id.childText,list);
            listView.setAdapter(adapter);
        }


        //FUNCTION FOR PARSING JSON
        public void jsonParsing(String json) {


            Log.d("Demo", "Parsing start");
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(json);

                Log.d("Demo/JsonParsing", "Json array created");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    id = object.getInt("id");
                    title = object.getString("title");
                    description = object.getString("description");
                    section = object.getString("section");
                    level = object.getString("level");
                    image_file_name = object.getString("image_file_name");
                    image_content_type = object.getString("image_content_type");
                    image_file_size = object.getString("image_file_size");
                    image_updated_at = object.getString("image_updated_at");
                    exam_date = object.getString("exam_date");
                    form_release_date = object.getString("form_release_date");
                    form_last_date = object.getString("form_last_date");
                    link1_name = object.getString("link1_name");
                    link1 = object.getString("link1");
                    link2_name = object.getString("link2_name");
                    link2 = object.getString("link2");
                    link3_name = object.getString("link3_name");
                    link3 = object.getString("link3");
                    link4_name = object.getString("link4_name");
                    link4 = object.getString("link4");
                    created_at = object.getString("created_at");
                    updated_at = object.getString("updated_at");
                    exam_review = object.getString("exam_review");
                    gen_fees_boys = object.getString("gen_fees_boys");
                    gen_fees_girls = object.getString("gen_fees_girls");
                    sc_fees_boys = object.getString("sc_fees_boys");
                    sc_fees_girls = object.getString("sc_fees_girls");
                    others_note = object.getString("others_note");
                    others = object.getString("others");

                    Log.d("Demo/JsonParsing", "Strings initialised");
                    //Adding one row to database.
                    long result=database.addData(id,title,description,section,level,image_file_name,image_content_type,image_file_size,image_updated_at, exam_date,form_release_date,form_last_date,link1_name,link1,link2_name,
                                        link2,link3_name,link3,link4_name,link4,created_at,updated_at,exam_review,gen_fees_boys,gen_fees_girls,sc_fees_boys,sc_fees_girls,others_note,others);

                    if(result!=-1){
                        Log.d("My","Added "+i);
                    }
                    else {
                        Log.d("My","Error ");
                    }
                }
            } catch (JSONException e) {
                Log.d("My", " " + e);
            }


        }


    }
}
