package com.buptcomputeacademic.lookforhealth.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 35390 on 2017/9/18.
 */

public class think_patient {
    public int id;

    public String name;

    public String username;

    public String password;

    @SerializedName("ph_number")
    public String phoneNumber;

    public String sex;

    public int age;
}
