package com.buptcomputeacademic.lookforhealth.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.buptcomputeacademic.lookforhealth.R;

/*
    设置软件的定位功能
 */
public class RouteMap extends AppCompatActivity {

    private MapView mapView;
    private boolean FistLocate=true;//第一次我的位置
    private BaiduMap baiduMap;//获取地图总控制
    public LocationClient locationClient;
    private PoiSearch poiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        locationClient=new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_routemap);
        mapView=(MapView) findViewById(R.id.BaiduMap);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        poiSearch=PoiSearch.newInstance();
        OnGetPoiSearchResultListener onGetPoiSearchResultListener=new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                //获取POT检索结果
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                //获取具体地点的详细信息
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                //获取室内的搜索结果
            }
        };
        poiSearch.setOnGetPoiSearchResultListener(onGetPoiSearchResultListener);
        initLocation();
        locationClient.start();

    }

    private void navigateTo(BDLocation bdLocation){
        if(FistLocate){
            LatLng latLng=new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            FistLocate=false;
        }
        MyLocationData.Builder builder=new MyLocationData.Builder();
        builder.latitude(bdLocation.getLatitude());
        builder.longitude(bdLocation.getLongitude());
        MyLocationData locationData=builder.build();
        baiduMap.setMyLocationData(locationData);
    }

    private void initLocation(){
        LocationClientOption locationClientOption=new LocationClientOption();
        locationClientOption.setScanSpan(5000);
        locationClient.setLocOption(locationClientOption);
    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}
