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


public class Links extends Fragment {

    TextView linkone,linktwo,linkthree,linkfour;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.links_xml,container,false);
        linkone=(TextView)v.findViewById(R.id.link_one);
        linktwo=(TextView)v.findViewById(R.id.link_two);
        linkthree=(TextView)v.findViewById(R.id.link_three);
        linkfour=(TextView)v.findViewById(R.id.link_four);


        linkone.setText(Database.link1_name);
        linktwo.setText(Database.link2_name);
        linkthree.setText(Database.link3_name);
        linkfour.setText(Database.link4_name);
            return v;
    }
}
