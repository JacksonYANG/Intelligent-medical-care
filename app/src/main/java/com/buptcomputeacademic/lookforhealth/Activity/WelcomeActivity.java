package com.buptcomputeacademic.lookforhealth.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.buptcomputeacademic.lookforhealth.R;

import java.util.ArrayList;
import java.util.List;

/*
    软件启动的欢迎界面，已经将危险权限全部申请，方便后面定位函数的调用
 */
public class WelcomeActivity extends AppCompatActivity {

    private ThreadDelay threadDelay;
    private boolean isrun=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        isrun=true;
        threadDelay=new ThreadDelay();
        threadDelay.start();
    }

    private class ThreadDelay extends Thread{
        @Override
        public void run() {
            if(isrun){
                try{
                    List<String> permissionList=new ArrayList<>();
                    if(ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                        permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                    }
                    if(ContextCompat.checkSelfPermission(WelcomeActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                        permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                    if(ContextCompat.checkSelfPermission(WelcomeActivity.this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
                        permissionList.add(Manifest.permission.READ_PHONE_STATE);
                    }
                    if(!permissionList.isEmpty()){
                        String []permissions=permissionList.toArray(new String[permissionList.size()]);
                        ActivityCompat.requestPermissions(WelcomeActivity.this,permissions,1);
                    }
                    else {
                        Thread.sleep(1500);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            handler.sendEmptyMessage(1);
        }
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    isrun=false;
                    threadDelay.interrupt();
                    threadDelay=null;
                    Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            AlertDialog.Builder builder=new AlertDialog.Builder(this);
                            builder.setTitle("错误").setMessage("必须同意所有权限才能使用本程序").setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   finish();
                                }
                            });
                            builder.show();
                        }
                    }
                    handler.sendEmptyMessage(1);
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("错误").setMessage("发生未知错误").setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.create().show();
                }
                break;
            default:
        }
    }
}
