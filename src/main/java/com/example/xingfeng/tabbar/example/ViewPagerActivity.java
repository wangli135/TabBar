package com.example.xingfeng.tabbar.example;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.xingfeng.tabbar.R;
import com.example.xingfeng.tabbar.view.TabBar;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends FragmentActivity {

    private TabBar mTabBar;

    private ViewPager mViewPager;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initFragments();

        mTabBar=(TabBar)findViewById(R.id.tabBar);
        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        mViewPager.setCurrentItem(0);

        //ViewPager滑动时Tab联动
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                mViewPager.setCurrentItem(position);
                mTabBar.setSelected(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Tab点击时ViewPager联动
        mTabBar.setOnTabSelectedListener(new TabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(View v) {

                mViewPager.setCurrentItem((Integer)v.getTag());

            }
        });

    }

    private void initFragments(){

        fragments=new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThridFragment());
        fragments.add(new FourthFragment());

    }

}
