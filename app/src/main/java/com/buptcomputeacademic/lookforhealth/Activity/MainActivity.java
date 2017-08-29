package com.buptcomputeacademic.lookforhealth.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.buptcomputeacademic.lookforhealth.Fragment.choose;
import com.buptcomputeacademic.lookforhealth.Fragment.me;
import com.buptcomputeacademic.lookforhealth.Fragment.procedure;
import com.buptcomputeacademic.lookforhealth.Fragment.route;
import com.buptcomputeacademic.lookforhealth.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShapeBadgeItem shapeBadgeItem=new ShapeBadgeItem().setShape(1).setShapeColorResource(R.color.colorAccent);
        TextBadgeItem textBadgeItem=new TextBadgeItem().setText("2").setHideOnSelect(true);

        BottomNavigationBar bottomNavigationBar=(BottomNavigationBar) findViewById(R.id.bottom_navigation);
        bottomNavigationBar.clearAll();

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.white);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_domain_white_24dp,"就诊医院").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_chrome_reader_mode_white_24dp,"流程").setActiveColor(R.color.MediumAquamarine))
                .addItem(new BottomNavigationItem(R.mipmap.ic_place_white_24dp,"地图").setActiveColor(R.color.cyan))
                .addItem(new BottomNavigationItem(R.mipmap.ic_perm_identity_white_24dp,"我的").setActiveColor(R.color.grey))
                .setFirstSelectedPosition(0).initialise();

        fragments=getFragments();
        setDefalutFragmet();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    //设置默认组件
    private void setDefalutFragmet(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,choose.newInstance("就诊医院"));
        fragmentTransaction.commit();
    }

    //获取组件信息
    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(choose.newInstance("就诊医院"));
        fragments.add(procedure.newInstance("流程"));
        fragments.add(route.newInstance("地图"));
        fragments.add(me.newInstance("我的"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        if(fragments!=null){
            if(position<fragments.size()){
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment fragment=fragments.get(position);
                if(fragment.isAdded()){
                    ft.replace(R.id.framelayout,fragment);
                } else{
                    ft.add(R.id.framelayout,fragment);
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {
        if(fragments!=null){
            if(position<fragments.size()){
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Fragment fragment=fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }
}
