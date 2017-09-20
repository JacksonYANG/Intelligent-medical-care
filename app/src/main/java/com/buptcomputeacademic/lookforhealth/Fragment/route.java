package com.buptcomputeacademic.lookforhealth.fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.buptcomputeacademic.lookforhealth.R;

import static com.buptcomputeacademic.lookforhealth.Base.loadPicture.decodeBitmapFromResource;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link route.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link route#newInstance} factory method to
 * create an instance of this fragment.
 */
public class route extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MapView mapView;
    private FrameLayout frameLayout;
    private boolean FistLocate=true;//第一次我的位置
    private BaiduMap baiduMap;//获取地图总控制
    public LocationClient locationClient;
    private PoiSearch poiSearch;

//    private OnFragmentInteractionListener mListener;

    public route() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment route.
     */
    // TODO: Rename and change types and number of parameters
    public static route newInstance(String param1) {
        route fragment = new route();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        locationClient=new LocationClient(getContext());
//        locationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getContext());
        mapView=(MapView) mapView.findViewById(R.id.BaiduMap);
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        poiSearch= PoiSearch.newInstance();
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
        locationClient.start();*/
    }

    /*private void navigateTo(BDLocation bdLocation){
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
    }*/

    /*private void initLocation(){
        LocationClientOption locationClientOption=new LocationClientOption();
        locationClientOption.setScanSpan(5000);
        locationClient.setLocOption(locationClientOption);
    }*/

    /*public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_route, container, false);
        Resources res=getResources();
        Bitmap bitmap=decodeBitmapFromResource(res,R.drawable.route_background,1280,720);
        frameLayout=(FrameLayout) view.findViewById(R.id.route_background);
        frameLayout.setBackground(new BitmapDrawable(res,bitmap));
        return view;
    }



//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//        mapView.onResume();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//        locationClient.stop();
//        mapView.onDestroy();
//        baiduMap.setMyLocationEnabled(false);
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
