package com.example.examopedia;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.examopedia.Adapters.ViewPagerAdapter;
import com.example.examopedia.SliderTab.SlidingTabLayout;


public class SwipeTab extends AppCompatActivity {
    Toolbar toolbar;
    SlidingTabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_tab);
        //toolbar=(Toolbar)findViewById(R.id.bar);
        //setSupportActionBar(toolbar);
        tabLayout=(SlidingTabLayout)findViewById(R.id.tabs);

        pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setDistributeEvenly(true);

        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabLayout.setViewPager(viewPager);
    }


}
