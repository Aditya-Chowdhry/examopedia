package com.example.examopedia.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examopedia.Database;
import com.example.examopedia.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.provider.CalendarContract.*;


public class Dates extends Fragment {


    TextView examdate, formrelease, formlast;
    ImageView imageView;
    Database database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dates_xml, container, false);
        examdate = (TextView) v.findViewById(R.id.examDate);
        formrelease = (TextView) v.findViewById(R.id.form_release);
        formlast = (TextView) v.findViewById(R.id.form_last);
        imageView = (ImageView) v.findViewById(R.id.alarm_image);
        database = new Database(getActivity());

        getDate();


        examdate.setText(Database.exam_date);
        formrelease.setText(Database.form_release_date);
        formlast.setText(Database.form_last_date);

        check();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAlarm();
            }
        });
        return v;
    }


    private void check() {
        if (Integer.parseInt(Database.alarm) == 1)
            imageView.setImageResource(R.drawable.alarm_on);

        else
            imageView.setImageResource(R.drawable.alarm_off);

    }

    private void changeAlarm() {
        long id = 0;

        String alarm = database.getAlarm(Database.title);
        if (Integer.parseInt(alarm) == 0) {
            imageView.setImageResource(R.drawable.alarm_on);

            Log.d("Mytag", "1");
            addevent();
        }


    }


    private void getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'");
        Date date = new Date();
        try {
            date = sdf.parse("2015-01-01T00:00:00.000Z");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Log.d("mytag", cal.get(Calendar.DATE) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR) + "\n" + date.toString());
        } catch (ParseException e) {
            Log.d("mytag", e + "");
        }

    }

    private void addevent() {

        database.updateColumn(Database.title);

        Calendar time = Calendar.getInstance();
        time.set(2015, 9, 9, 7, 00);
        Calendar timeNew = Calendar.getInstance();
        timeNew.set(2015, 9, 9, 8, 00);


        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(Events.TITLE, Database.title)
                .putExtra(Events.EVENT_LOCATION, "mew")

                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, timeNew.getTimeInMillis());
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }


    }
}
