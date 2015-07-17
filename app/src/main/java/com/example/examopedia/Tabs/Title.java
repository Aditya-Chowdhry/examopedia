package com.example.examopedia.Tabs;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examopedia.Database;
import com.example.examopedia.R;


public class Title extends Fragment {

    TextView title,desc;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.title_xml,container,false);
        title=(TextView)v.findViewById(R.id.examName);
        desc=(TextView)v.findViewById(R.id.description);


        title.setText(Database.title);
        desc.setText(Database.description);
            return v;
    }
}
