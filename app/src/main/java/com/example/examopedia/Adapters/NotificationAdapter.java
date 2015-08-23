package com.example.examopedia.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examopedia.NotificationActivity.Model;
import com.example.examopedia.R;
import com.example.examopedia.WebLinks;

import java.util.List;

/**
 * Created by dilpreet on 23/8/15.
 */
public class NotificationAdapter extends BaseAdapter {
    List<Model> modelList;
    Context context;
    LayoutInflater inflater;
    TextView title,desc,linkOneName,linkTwoName;


    public NotificationAdapter(List<Model> models,Context context) {
        super();
        modelList=models;
        this.context=context;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static String linkOne,linkTwo;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(inflater==null)
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView=inflater.inflate(R.layout.item,null);

        title=(TextView)convertView.findViewById(R.id.titleN);
        desc=(TextView)convertView.findViewById(R.id.descN);
        linkOneName=(TextView)convertView.findViewById(R.id.link1N);
        linkTwoName=(TextView)convertView.findViewById(R.id.link2N);

        final Model currentItem=modelList.get(position);



        title.setText(currentItem.getTitle());
        desc.setText(currentItem.getDescription());
        linkOneName.setText(currentItem.getLink1_name());
        linkTwoName.setText(currentItem.getLink2_name());

        linkOneName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkOne=currentItem.getLink1();
                linkTwo=currentItem.getLink2();


                Intent intent=new Intent(context, WebLinks.class);
                intent.putExtra("link",linkOne);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
