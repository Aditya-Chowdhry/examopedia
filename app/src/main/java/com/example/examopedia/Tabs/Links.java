package com.example.examopedia.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examopedia.Database;
import com.example.examopedia.R;
import com.example.examopedia.WebLinks;


public class Links extends Fragment {

    TextView linkone,linktwo,linkthree,linkfour,link_one;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.links_xml,container,false);
        linkone=(TextView)v.findViewById(R.id.link_one);
        linktwo=(TextView)v.findViewById(R.id.link_two);
        linkthree=(TextView)v.findViewById(R.id.link_three);
        linkfour=(TextView)v.findViewById(R.id.link_four);
        linkone.setText("www.google.com");
        linktwo.setText("www.google.com");
       // linkthree.setText(Database.link3_name);
        //linkfour.setText(Database.link4_name);
        link_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempLink="www.google.com";
                Intent i=new Intent(Links.this.getActivity(),WebLinks.class);
                i.putExtra("link",tempLink);
                startActivity(i);
            }
        });

        return v;
    }
    public void openLink(View view){

    }
}
