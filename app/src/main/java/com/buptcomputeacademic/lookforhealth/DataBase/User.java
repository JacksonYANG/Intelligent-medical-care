package com.buptcomputeacademic.lookforhealth.DataBase;

import org.litepal.crud.DataSupport;

/**
 * Created by 35390 on 2017/9/18.
 */

public class User extends DataSupport {

    private String user_name;
    private String password;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
