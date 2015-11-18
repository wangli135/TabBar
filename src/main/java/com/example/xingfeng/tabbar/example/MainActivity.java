package com.example.xingfeng.tabbar.example;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.xingfeng.tabbar.R;
import com.example.xingfeng.tabbar.view.TabBar;

public class MainActivity extends AppCompatActivity {

    private TabBar mTabBar;

    private Fragment[] fragments={new FirstFragment(),new SecondFragment(),new ThridFragment(),new FourthFragment()};

    private Fragment currentFragment=fragments[0];//当前显示的Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager=getFragmentManager();
        final FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.content,currentFragment);
        transaction.commit();

        mTabBar=(TabBar)findViewById(R.id.tabBar);

        mTabBar.setOnTabSelectedListener(new TabBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(View v) {

                FragmentManager manager=getFragmentManager();
                FragmentTransaction myTransaction=manager.beginTransaction();
                currentFragment=fragments[(Integer) v.getTag()];
                myTransaction.replace(R.id.content,currentFragment);
                myTransaction.commit();


            }
        });
    }


}
