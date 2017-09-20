package com.buptcomputeacademic.lookforhealth.web;

/**
 * Created by 35390 on 2017/9/2.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
