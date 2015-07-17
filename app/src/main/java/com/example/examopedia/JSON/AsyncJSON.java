package com.example.examopedia.JSON;

import android.content.Context;
import android.os.AsyncTask;

public class AsyncJSON extends AsyncTask<String,Void,Void>{
    Context context;
    ParseJSON parseJSON;
    public AsyncJSON(Context context) {
        super();
        this.context=context;

    }

    @Override
    protected Void doInBackground(String... params) {
        parseJSON=new ParseJSON(context);
        parseJSON.jsonParsing(params[0]);
        return null;
    }
}
