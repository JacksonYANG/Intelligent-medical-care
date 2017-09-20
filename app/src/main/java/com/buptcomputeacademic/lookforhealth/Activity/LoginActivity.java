package com.buptcomputeacademic.lookforhealth.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.buptcomputeacademic.lookforhealth.DataBase.User;
import com.buptcomputeacademic.lookforhealth.R;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    登录界面以及注册界面，允许用户在程序中注册并且将注册后的信息上传至服务器中，登录时也可进行登陆时的用户名密码匹配
 */
public class LoginActivity extends AppCompatActivity {

    private Button loginbutton;
    private Button registerbutton;
    private CheckBox saveuser;
    private EditText logintext;
    private EditText passwordtext;
    private boolean isLogin=false;
    private boolean isremember=false;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean flag=true;//用来判断用户信息是否在本地数据库中有储存


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        loginbutton=(Button) findViewById(R.id.login);
        registerbutton=(Button) findViewById(R.id.register);
        logintext=(EditText) findViewById(R.id.login_text);
        passwordtext=(EditText) findViewById(R.id.password_text);
        saveuser=(CheckBox) findViewById(R.id.rememberuser);

        //获取网络连接状态,没有网络直接退出
        ConnectivityManager connectivityManager=(ConnectivityManager) LoginActivity.this.getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null){
            AlertDialog.Builder build=new AlertDialog.Builder(this);
            build.setTitle("错误").setMessage("没有网络连接,程序即将退出").setCancelable(false).setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }

        //保存用户后下一次启动调出上一次的用户名和密码
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        isremember=sharedPreferences.getBoolean("rememberpass",false);

        if(isremember){
            String accout=sharedPreferences.getString("account","");
            String password=sharedPreferences.getString("password","");
            logintext.setText(accout);
            passwordtext.setText(password);
            saveuser.setChecked(true);
        }

        //点击注册按钮，进入注册界面
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterPage.class);
                startActivity(intent);
            }
        });

        //点击登陆按钮，提交数据给服务器进行判断
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=logintext.getText().toString();
                String password=passwordtext.getText().toString();


                ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("登陆中");
                progressDialog.setMessage("正在登陆...");
                progressDialog.setCancelable(true);
                progressDialog.show();//耗时登陆逻辑在线程服务中进行

                //如果本地中有保存用户用户名和密码，直接在本地中查找
                List<User> queryUser= DataSupport.findAll(User.class);
                for(User user1:queryUser){
                    if((user1.getUser_name().equals(account))&&(user1.getPassword().equals(password))){
                        flag=false;
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                }

//                Map<String,String> values=null;
//                if(account!=""&&password!=""){
//                    editor=sharedPreferences.edit();
//                    values=new HashMap<String,String>();
//                    values.put("user_account",account);
//                    values.put("user_password",password);
//
//                    //设置登录成功后SharePreference作用
//                    if(saveuser.isChecked()){
//                        editor.putBoolean("rememberpass",true);
//                        editor.putString("account",account);
//                        editor.putString("password",password);
//                    } else {
//                        editor.clear();
//                    }
//                    editor.commit();
//                }
//                else{
//                    final AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
//                    builder.setTitle("错误").setMessage("用户名或密码错误").setCancelable(true).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).create().show();
//                }
            }
        });

    }
}
