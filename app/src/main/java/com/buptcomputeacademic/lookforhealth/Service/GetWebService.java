package com.buptcomputeacademic.lookforhealth.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

import com.buptcomputeacademic.lookforhealth.activity.LoginActivity;

import java.util.ArrayList;

public class GetWebService extends Service implements Runnable {
    private Thread thread;
    private static ArrayList<Task> tasks=new ArrayList<Task>();
    private boolean isrun=true;

    @Override
    public void onCreate() {
        super.onCreate();
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(thread==null){
            thread=new Thread(this);
            thread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    //新任务
    public static void NewTask(Task task){
        for(Task task1:tasks){
            if(task1.getActivity()==task.getActivity()&&task1.getTaskId()==task.getTaskId()){
                Toast.makeText(task.getActivity(),"正在获取信息，请等待...",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        tasks.add(task);
    }

    //判断任务
    private void StartTask(Task task){
        switch (task.getTaskId()){

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void run() {
        while(isrun){
            if(tasks.size()>0){
                Task task=tasks.get(0);
                StartTask(task);
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e){

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.interrupt();
        isrun=false;
        thread=null;
    }
}
