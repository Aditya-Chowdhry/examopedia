package com.examopedia.beta.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.examopedia.beta.Database;
import com.examopedia.beta.R;
import com.examopedia.beta.WebLinks;


public class Links extends Fragment {

    TextView linkone,linktwo,linkthree,linkfour,one,two,three,four;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.links_xml,container,false);
        linkone=(TextView)v.findViewById(R.id.link_one);
        linktwo=(TextView)v.findViewById(R.id.link_two);
        linkthree=(TextView)v.findViewById(R.id.link_three);
        linkfour=(TextView)v.findViewById(R.id.link_four);
        one= (TextView) v.findViewById(R.id.one);
        two= (TextView) v.findViewById(R.id.two);
        three= (TextView) v.findViewById(R.id.three);
        four= (TextView) v.findViewById(R.id.four);

        linkone.setText("https://www.google.co.in/");
        one.setText("Link 1 :");
        linktwo.setText("https://www.google.co.in/");
        two.setText("Link 2 :");
        three.setText("Link 3 :");
        linkthree.setText(Database.link3);
        four.setText("Link 4 :");
        linkfour.setText(Database.link4);

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
        linkone.setVisibility(View.VISIBLE);
        linktwo.setVisibility(View.VISIBLE);
        linkthree.setVisibility(View.VISIBLE);
        linkfour.setVisibility(View.VISIBLE);
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);

        if(Database.link3=="null")
        {
            linkthree.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
        }
        if(Database.link1==null)
        {
            linkone.setVisibility(View.GONE);
            one.setVisibility(View.GONE);
        }
        if(Database.link2==null)
        {
            linktwo.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
        }
        if(Database.link4=="null")
        {
            linkfour.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
        }

       // linkthree.setText(Database.link3_name);
        //linkfour.setText(Database.link4_name);


        return v;
    }

    private void start(){
        Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(getActivity(),WebLinks.class);
        intent.putExtra("link","abcd");
        startActivity(intent);
    }

}
