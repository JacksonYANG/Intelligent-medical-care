package com.buptcomputeacademic.lookforhealth.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buptcomputeacademic.lookforhealth.R;
import com.buptcomputeacademic.lookforhealth.activity.Accomplish;
import com.buptcomputeacademic.lookforhealth.activity.AccomplishDetail;
import com.buptcomputeacademic.lookforhealth.web.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.buptcomputeacademic.lookforhealth.Base.loadPicture.decodeBitmapFromResource;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link procedure#newInstance} factory method to
 * create an instance of this fragment.
 */
public class procedure extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FrameLayout frameLayout;
    private Button Delay;
    private Button DelayBack;
    private TextView beforePerson;
    private TextView currentRoom;
    private TextView nextRoom;

//    private OnFragmentInteractionListener mListener;

    public procedure() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment procedure.
     */
    // TODO: Rename and change types and number of parameters
    public static procedure newInstance(String param1) {
        procedure fragment = new procedure();
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
        //初始化按钮
        View view=inflater.inflate(R.layout.fragment_procedure, container, false);
//        Resources res=getResources();
//        Bitmap bitmap=decodeBitmapFromResource(res,R.drawable.procedure_background,1280,720);
//        frameLayout=(FrameLayout) view.findViewById(R.id.procedure_background);
//        frameLayout.setBackground(new BitmapDrawable(res,bitmap));
        Delay=(Button) view.findViewById(R.id.delay);
        DelayBack=(Button) view.findViewById(R.id.delay_back);

        //初始化三个Textview
        beforePerson=(TextView) view.findViewById(R.id.before_person);
        currentRoom=(TextView) view.findViewById(R.id.current_room);
        nextRoom=(TextView) view.findViewById(R.id.next_room);



        Delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),AccomplishDetail.class);
                startActivity(intent);
            }
        });

        DelayBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    public void requestServer(final String patientId){
        String Hostaddress=null;
        HttpUtil.sendOkHttpRequest(Hostaddress, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"获取医院信息失败，请检查网络连接",Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String resposeText=response.body().string();

            }
        });
    }


}
