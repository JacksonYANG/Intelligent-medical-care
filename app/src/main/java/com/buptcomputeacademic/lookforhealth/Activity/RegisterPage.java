package com.buptcomputeacademic.lookforhealth.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.buptcomputeacademic.lookforhealth.R;

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {

    private EditText NewAccout;
    private EditText NewPasswd;
    private EditText phone;
    private Button SendCaptcha;
    private Button Commit;
    private Button Cancel;
    private String mNewAccout;
    private String mNewPasswd;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_page);
        NewAccout=(EditText) findViewById(R.id.NewUserName);
        NewPasswd=(EditText) findViewById(R.id.NewPassword);
        phone=(EditText) findViewById(R.id.PhoneNumber);
        SendCaptcha=(Button) findViewById(R.id.sendcaptcha);
        Commit=(Button) findViewById(R.id.commit);
        Cancel=(Button) findViewById(R.id.cancel);
        mNewAccout=NewAccout.getText().toString();
        mNewPasswd=NewPasswd.getText().toString();
        mPhone=phone.getText().toString();

        SendCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getchaptcha();
            }
        });

        Commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(RegisterPage.this);
                builder.setMessage("确定取消注册新用户吗?").setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    public void getchaptcha(){
        String host="https://sms.253.com/msg/send";
        String path="sendSms";
        String method="GET";
        String appcode="我的appcode";


    }
}
