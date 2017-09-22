package com.buptcomputeacademic.lookforhealth.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.buptcomputeacademic.lookforhealth.DataBase.Patient;
import com.buptcomputeacademic.lookforhealth.R;
import com.google.gson.annotations.SerializedName;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Accomplish extends AppCompatActivity {

//    private Spinner Sex;
    private ImageView headPhoto;
    private EditText patientName;
    private EditText patientAge;
    private EditText patientPhone;
    private EditText patientHeight;
    private EditText patientWeight;
    private EditText patientSex;
    private Button sendToDetail;
    private Button Back;
    private Button loadHeadPhoto;
//    private String sPatientSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish);
        //初始化控件
//        final Resources res=getResources();
//        Sex=(Spinner) findViewById(R.id.sex_select);
        headPhoto=(ImageView) findViewById(R.id.head_photo);
        loadHeadPhoto=(Button) findViewById(R.id.load_headphoto);
        sendToDetail=(Button) findViewById(R.id.send_to_detail);
        Back=(Button) findViewById(R.id.back);
        patientName=(EditText) findViewById(R.id.patient_name);
        patientAge=(EditText) findViewById(R.id.patient_age);
        patientPhone=(EditText) findViewById(R.id.patient_phone);
        patientHeight=(EditText) findViewById(R.id.patient_height);
        patientWeight=(EditText) findViewById(R.id.patient_weight);
        patientSex=(EditText) findViewById(R.id.patient_sex);

        //上传头像功能实现
        loadHeadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //判断性别
//        Sex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                sPatientSex=res.getStringArray(R.array.sex)[position];
//            }
//        });
        //下一步进入血糖监测等详细功能
        sendToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sPatientName=patientName.getText().toString();
                String sPatientAge=patientAge.getText().toString();
                String sPatientPhone=patientPhone.getText().toString();
                String sPatientHeight=patientHeight.getText().toString();
                String sPatientWeight=patientWeight.getText().toString();
                String sPatientSex=patientSex.getText().toString();

                if(TextUtils.isEmpty(sPatientName)){
                    patientName.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入名字",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sPatientAge)){
                    patientAge.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入年龄",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sPatientPhone)){
                    patientPhone.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sPatientHeight)){
                    patientHeight.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入身高",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sPatientWeight)){
                    patientWeight.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入体重",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sPatientSex)){
                    patientSex.requestFocus();
                    Toast.makeText(Accomplish.this,"请输入性别",Toast.LENGTH_SHORT).show();
                    return;
                }

                LitePal.getDatabase();
//                List<Patient> patientList= DataSupport.findAll(Patient.class);
                Patient patient=new Patient();
                patient.setPatient_name(sPatientName);
                patient.setPatient_age(sPatientAge);
                patient.setPatient_phone(sPatientPhone);
                patient.setPatient_sex(sPatientSex);
                patient.setPatient_height(sPatientHeight);
                patient.setPatient_weight(sPatientWeight);
                patient.save();
                AlertDialog.Builder builder=new AlertDialog.Builder(Accomplish.this);
                builder.setTitle("成功");
                builder.setMessage("基本信息完善成功");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(Accomplish.this,AccomplishDetail.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.create().show();

            }
        });

        //返回按钮
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
