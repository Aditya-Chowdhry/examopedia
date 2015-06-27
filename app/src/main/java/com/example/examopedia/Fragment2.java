package com.example.examopedia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    View view;
TextView txt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_two, container, false);
        txt=(TextView)view.findViewById(R.id.date);
        Bundle args=getArguments();
        txt.setText(args.getString("date"));

        return view;
    }

}
