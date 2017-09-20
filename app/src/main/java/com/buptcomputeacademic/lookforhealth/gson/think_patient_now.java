package com.buptcomputeacademic.lookforhealth.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 35390 on 2017/9/18.
 */

public class think_patient_now {
    public int id;

    public String password;

    public String destination;

    @SerializedName("pos")
    public int position;
}
