package com.example.examopedia;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        aSwitch=(Switch)findViewById(R.id.switchOne);

        sharedPreferences=getSharedPreferences("alarm", MODE_PRIVATE);
        editor=sharedPreferences.edit();

        int getKey=sharedPreferences.getInt("alarmKey",1);

        if(getKey==1)
            aSwitch.setChecked(true);
        else
            aSwitch.setChecked(false);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int result = -1;

                if (isChecked)
                {   MainActivity.startReciver();
                    result = 1;
                }
                else
                {   MainActivity.cancelAlarm();
                    result = 0;
                }
                Log.d("Mytag", result + "");
                editor.putInt("alarmKey", result);
                editor.commit();
            }
        });
    }


}
