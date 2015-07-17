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


public class Fees extends Fragment {

    TextView genboys,gengirls,scboys,scgirls;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fees_xml,container,false);
        genboys=(TextView)v.findViewById(R.id.gen_boys);
        gengirls=(TextView)v.findViewById(R.id.gen_girls);
        scboys=(TextView)v.findViewById(R.id.sc_boys);
        scgirls=(TextView)v.findViewById(R.id.sc_girls);

        genboys.setText(Database.gen_fees_boys);
        gengirls.setText(Database.gen_fees_girls);
        scboys.setText(Database.sc_fees_boys);
        scgirls.setText(Database.sc_fees_girls);
            return v;
    }
}
