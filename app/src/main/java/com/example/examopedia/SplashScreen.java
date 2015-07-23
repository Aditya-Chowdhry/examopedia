package com.example.examopedia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class SplashScreen extends AppCompatActivity {

    protected int timer=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread splashThread=new Thread(){

            @Override
            public void run(){
               try {
                   sleep(timer);
                   startActivity(new Intent(SplashScreen.this,MainActivity.class));
               }
               catch (Exception e){
               }
               finally {
                   finish();
               }
           }
       };
        splashThread.start();
    }

}
