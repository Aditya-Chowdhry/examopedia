package com.example.examopedia.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.examopedia.Tabs.Dates;
import com.example.examopedia.Tabs.Fees;
import com.example.examopedia.Tabs.Links;
import com.example.examopedia.Tabs.Title;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0) {
            Title title =new Title();
            return title;
        }
        else if(position==1){
            Dates dates=new Dates();
            return dates;
        }
        else if(position==2){
            Links links=new Links();
            return links;
        }
        else if(position==3){
            Fees fees=new Fees();
            return fees;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String str=null;
        if(position==0)
            str="Exam";
        else if(position==1)
            str="Dates";
        else if(position==2)
            str="Links";
        else if(position==3)
            str="Fees";
        return str;
    }
}
