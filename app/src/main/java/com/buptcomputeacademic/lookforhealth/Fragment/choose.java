package com.buptcomputeacademic.lookforhealth.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.buptcomputeacademic.lookforhealth.R;
import com.buptcomputeacademic.lookforhealth.activity.Accomplish;
import com.buptcomputeacademic.lookforhealth.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.buptcomputeacademic.lookforhealth.Base.loadPicture.decodeBitmapFromResource;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link choose#newInstance} factory method to
 * create an instance of this fragment.
 */
public class choose extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FrameLayout frameLayout;
    private Button Accomplish_btn;
    private Button Order;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public choose() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment choose.
     */
    // TODO: Rename and change types and number of parameters
    public static choose newInstance(String param1) {
        choose fragment = new choose();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_choose,container,false);
        //设置三个按钮并且进行初始化
        Accomplish_btn=(Button) view.findViewById(R.id.accomplish);
        Order=(Button) view.findViewById(R.id.order);

        Accomplish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入完善信息的intent
                Intent intent=new Intent(getContext(), Accomplish.class);
                startActivity(intent);
            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        Resources res=getResources();
//        Bitmap bitmap=decodeBitmapFromResource(res,R.drawable.choose_background,1280,720);
//
//        frameLayout=(FrameLayout) view.findViewById(R.id.choose_background);
//        frameLayout.setBackground(new BitmapDrawable(res,bitmap));


        return view;
    }

}
