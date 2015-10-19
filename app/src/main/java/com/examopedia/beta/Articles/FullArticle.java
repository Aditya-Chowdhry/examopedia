package com.examopedia.beta.Articles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.examopedia.beta.R;
import com.examopedia.beta.SingletonRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class FullArticle extends AppCompatActivity {
    ProgressDialog progressDialog;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article);
        Intent intent = getIntent();
        id = intent.getExtras().getString("id");

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait .");
        progressDialog.show();
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_article, menu);
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

    private void getData(){

        RequestQueue queue = SingletonRequest.getInstance(this.getApplicationContext()).
                getRequestQueue();
        //How to check from localhost
        //http://stackoverflow.com/questions/6760585/accessing-localhostport-from-android-emulator

        String url="https://intense-brook-1791.herokuapp.com/articles/"+id+".json";
        Log.d("FullArticle", url);
        JsonObjectRequest request=new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing json object response
                            // response will be a json object
                            TextView tv1,tv2,tv3,tv4;
                            String title = response.getString("title");
                            String body = response.getString("body");
                            String authorName = response.getString("author_name");
                            String authorLink = response.getString("author_link");

                            tv1=(TextView)findViewById(R.id.fullTitle);
                            tv1.setText(title);

                            tv2=(TextView)findViewById(R.id.articleBody);
                            tv2.setText(body);


                            tv3=(TextView)findViewById(R.id.articleAuthorName);
                            tv3.setText(authorName);


                            tv4=(TextView)findViewById(R.id.articleAuthorLink);
                            tv4.setText(authorLink);

                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("Articles", error.toString());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }

    private void parseJSON(JSONObject json){


        try{




        } catch (Exception e1) {

            Log.d("Article", e1.toString());
            e1.printStackTrace();
        }


    }

}
