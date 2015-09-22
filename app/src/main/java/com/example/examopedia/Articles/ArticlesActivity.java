package com.example.examopedia.Articles;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.examopedia.SingletonRequest;
import com.example.examopedia.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {
    ArrayList<ArticleInfo> articleList;
    ProgressDialog progressDialog;
    ArticlesListAdapter articleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        articleList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait .");
        progressDialog.show();


        //Why Recycler View?
        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        //recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        articleAdapter= new ArticlesListAdapter(articleList);
        recList.setAdapter(articleAdapter);
        //end of Recycler view!

        getData();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_articles, menu);
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

    private List<ArticleInfo> createList(int size) {

        List<ArticleInfo> result = new ArrayList<ArticleInfo>();
        for (int i=1; i <= size; i++) {
            ArticleInfo ai = new ArticleInfo();
            ai.title = "Test" + i;
            ai.bodyText="BodyText"+ i;
            ai.author_name = "AuthorName" + i;
            ai.author_link = "AuthorLink" + i;

            result.add(ai);

        }

        return result;
    }

    private void getData(){

        RequestQueue queue = SingletonRequest.getInstance(this.getApplicationContext()).
                getRequestQueue();
        //How to check from localhost
        //http://stackoverflow.com/questions/6760585/accessing-localhostport-from-android-emulator

        String url="https://intense-brook-1791.herokuapp.com/articles.json";
        Log.d("Articles",url);
        StringRequest request=new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
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

    private void parseJSON(String json){
        JSONArray array;

        try{
            array =new JSONArray(json);
            JSONObject object;
            for(int i=array.length()-1;i>=0;i--){
                object=array.getJSONObject(i);
                ArticleInfo newItem=new ArticleInfo();
                newItem.setTitle(object.getString("title"));
                newItem.setBodyText(object.getString("body"));
                newItem.setAuthor_name(object.getString("author_name"));
                newItem.setAuthor_link(object.getString("author_link"));

                articleList.add(newItem);

            }
            articleAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }
        catch (Exception e){
            Log.d("Article",e.toString());
        }

    }
}
