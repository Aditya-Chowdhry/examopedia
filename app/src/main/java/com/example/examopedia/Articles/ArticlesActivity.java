package com.example.examopedia.Articles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.examopedia.R;

import java.util.ArrayList;
import java.util.List;

public class ArticlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        //Why Recycler View?

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        ArticlesListAdapter articlesAdapter = new ArticlesListAdapter(createList(30));
        recList.setAdapter(articlesAdapter);
        //end of Recycler view!
        
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
}
