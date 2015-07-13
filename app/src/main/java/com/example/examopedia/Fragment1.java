package com.example.examopedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment1 extends Fragment {
    TextView txt;
    View view;
    String str;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_one, container, false);
        txt=(TextView)view.findViewById(R.id.about);
        Bundle args=getArguments();
        txt.setText(args.getString("about"));
        return view;

    }

}
