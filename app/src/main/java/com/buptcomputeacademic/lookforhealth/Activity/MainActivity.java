package com.buptcomputeacademic.lookforhealth.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.buptcomputeacademic.lookforhealth.Base.ViewPagerAdapter;
import com.buptcomputeacademic.lookforhealth.fragment.choose;
import com.buptcomputeacademic.lookforhealth.fragment.procedure;
import com.buptcomputeacademic.lookforhealth.fragment.route;
import com.buptcomputeacademic.lookforhealth.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> titleArrayList=new ArrayList<String>(){{
        add("排号");
        add("流程");
        add("路线图");
    }};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化viewpager容器和TableLayout
        fragmentArrayList=getFragements();
        tabLayout=(TabLayout) findViewById(R.id.main_table);
        viewPager=(ViewPager) findViewById(R.id.main_viewpager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),fragmentArrayList,titleArrayList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager,true);
        tabLayout.getTabAt(0).setIcon(getDrawable(R.drawable.choose_icon));
        tabLayout.getTabAt(1).setIcon(getDrawable(R.drawable.procedure_icon));
        tabLayout.getTabAt(2).setIcon(getDrawable(R.drawable.route_icon));

    }

    private ArrayList<Fragment> getFragements(){
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(new choose());
        fragments.add(new procedure());
        fragments.add(new route());
        return fragments;
    }
}
