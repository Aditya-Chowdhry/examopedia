package com.example.examopedia;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.os.Bundle;


public class Details extends FragmentActivity {
  //  TextView text;
    String about,date,fees;
    ViewPager viewPager;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //text=(TextView) findViewById(R.id.textview);
        about=getIntent().getStringExtra("about");
        date=getIntent().getStringExtra("date");
        fees=getIntent().getStringExtra("fees");

        //text.setText(str);
        viewPager=(ViewPager)findViewById(R.id.pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        viewPager.setAdapter(new Myad(fragmentManager));

    }



    class Myad extends FragmentStatePagerAdapter {

        public Myad(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int pos) {
            Fragment f=null;
            if(pos==0){
                f=new Fragment1();
                Bundle args=new Bundle();
                args.putString("about",about);
                f.setArguments(args);
                return f;
                           }
            if(pos==1){
                f=new Fragment2();
                Bundle args=new Bundle();
                args.putString("date",date);
                f.setArguments(args);
                return f;
            }
            if(pos==2){
                f=new Fragment3();
                Bundle args=new Bundle();
                args.putString("fees",fees);
                f.setArguments(args);
                return f;
            }

            return f;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            if(position==0)
                return "About";
            if(position==1)
                return "Date";
            if(position==2)
                return "Fees";

            return null;
        }

}

}
