package com.example.examopedia.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
        linkone.setText("https://www.google.co.in/");
        linktwo.setText("https://www.google.co.in/");
        linkthree.setText("https://www.google.co.in/");
        linkfour.setText("https://www.google.co.in/");

        linkone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        linktwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        linkthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        linkfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
       // linkthree.setText(Database.link3_name);
        //linkfour.setText(Database.link4_name);


        return v;
    }

    private void start(){
        Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getActivity(),WebLinks.class);
        intent.putExtra("link","https://www.google.co.in/");
        startActivity(intent);
    }

}
