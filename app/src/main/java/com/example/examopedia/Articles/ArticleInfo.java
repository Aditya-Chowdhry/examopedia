package com.example.examopedia.Articles;

/**
 * Created by aditya on 22/9/15.
 */
public class ArticleInfo {
    public void setAuthor_link(String author_link) {
        this.author_link = author_link;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String title;
    protected String bodyText;
    protected String author_name;
    protected String author_link;

}
