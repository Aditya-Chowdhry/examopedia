package com.example.examopedia.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examopedia.Database;
import com.example.examopedia.R;


public class Dates extends Fragment {

    TextView examdate,formrelease,formlast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.dates_xml,container,false);
        examdate=(TextView)v.findViewById(R.id.examDate);
        formrelease=(TextView)v.findViewById(R.id.form_release);
        formlast=(TextView)v.findViewById(R.id.form_last);


        examdate.setText(Database.exam_date);
        formrelease.setText(Database.form_release_date);
        formlast.setText(Database.form_last_date);
            return v;
    }
}
