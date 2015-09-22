package com.example.examopedia.Articles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examopedia.R;

import java.util.List;

/**
 * Created by aditya on 22/9/15.
 *https://www.binpress.com/tutorial/android-l-recyclerview-and-cardview-tutorial/156
 *
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.CardViewHolder> {

    private List<ArticleInfo> articleList;

    ArticlesListAdapter(List<ArticleInfo> articleList){
        this.articleList=articleList;
    }

    @Override
    public ArticlesListAdapter.CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_cardview, viewGroup, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticlesListAdapter.CardViewHolder cardViewHolder, int i) {
        ArticleInfo ai = articleList.get(i);
        cardViewHolder.vTitle.setText(ai.title);
        cardViewHolder.vAuthorName.setText(ai.author_name);
        cardViewHolder.vAuthorLink.setText(ai.author_link);
        cardViewHolder.vBodyText.setText(ai.bodyText);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder{
        protected TextView vTitle;
        protected TextView vAuthorName;
        protected TextView vAuthorLink;
        protected TextView vBodyText;

        public CardViewHolder(View itemView) {
            super(itemView);
            vTitle=(TextView)itemView.findViewById(R.id.articleTitle);
            vAuthorName=(TextView)itemView.findViewById(R.id.authorName);
            vAuthorLink=(TextView)itemView.findViewById(R.id.authorLink);
            vBodyText=(TextView)itemView.findViewById(R.id.bodyText);
        }

    }
}
