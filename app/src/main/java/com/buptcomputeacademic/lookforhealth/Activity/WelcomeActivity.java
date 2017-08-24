package com.buptcomputeacademic.lookforhealth.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.buptcomputeacademic.lookforhealth.R;

public class WelcomeActivity extends AppCompatActivity {

    private ThreadDelay threadDelay;
    private boolean isrun=false

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
                    Thread.sleep(1500);
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
}
