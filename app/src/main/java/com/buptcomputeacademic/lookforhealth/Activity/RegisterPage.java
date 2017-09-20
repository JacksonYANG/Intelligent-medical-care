package com.buptcomputeacademic.lookforhealth.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.buptcomputeacademic.lookforhealth.DataBase.User;
import com.buptcomputeacademic.lookforhealth.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.sms.SMSSDK;
import cn.jpush.sms.listener.SmscheckListener;
import cn.jpush.sms.listener.SmscodeListener;
import static com.buptcomputeacademic.lookforhealth.Base.loadPicture.*;

public class RegisterPage extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText NewAccout;
    private EditText NewPasswd;
    private EditText phone;
    private EditText captcha;
    private Button SendCaptcha;
    private Button Commit;
    private Button Cancel;
    private String mNewAccount;
    private String mNewPasswd;
    private String mPhone;
    private String mChapcha;
    private ProgressDialog registerDialog;//登陆时候的进度条
    private TimerTask timerTask;//定时器任务
    private Timer timer;//定时器
    private boolean flag=true;
    private int remainTime;//定时器剩余时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_page);
        Resources res=getResources();
        Bitmap bitmap=decodeBitmapFromResource(res,R.drawable.register_background,1280,720);
        linearLayout=(LinearLayout) findViewById(R.id.register_page_layout);
        linearLayout.setBackground(new BitmapDrawable(res,bitmap));
        registerDialog=new ProgressDialog(this);
        NewAccout=(EditText) findViewById(R.id.NewUserName);
        NewPasswd=(EditText) findViewById(R.id.NewPassword);
        phone=(EditText) findViewById(R.id.PhoneNumber);
        captcha=(EditText) findViewById(R.id.captcha);
        SendCaptcha=(Button) findViewById(R.id.sendcaptcha);
        Commit=(Button) findViewById(R.id.commit);
        Cancel=(Button) findViewById(R.id.cancel);


        SendCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhone=phone.getText().toString();
                if(TextUtils.isEmpty(mPhone)){
                    Toast.makeText(RegisterPage.this,"输入手机号才能进行验证哦！",Toast.LENGTH_SHORT).show();
                    return;
                }
                getchaptcha();//一个私有函数,发送手机验证码
            }
        });

        Commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewAccount=NewAccout.getText().toString();
                mNewPasswd=NewPasswd.getText().toString();
                mPhone=phone.getText().toString();
                mChapcha=captcha.getText().toString();
                if(TextUtils.isEmpty(mNewAccount)){
                    Toast.makeText(RegisterPage.this,"请输入注册账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mNewPasswd)){
                    Toast.makeText(RegisterPage.this,"请输入注册密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mPhone)){
                    Toast.makeText(RegisterPage.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mChapcha)){
                    Toast.makeText(RegisterPage.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                registerDialog.setTitle("注册中...");
                registerDialog.show();
                //检查验证码是否正确
                checkChaptcha();
                //调用函数将用户信息存至本地,若用户不在本地，则在服务器中查找
                saveuser();

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

    private void getchaptcha(){
        //调用极光短信验证码接口，对手机发送验证码，成功返回uuid,失败返回错误信息
        mPhone=phone.getText().toString();
        if(TextUtils.isEmpty(mPhone)){
            Toast.makeText(RegisterPage.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            phone.getText().clear();
            phone.requestFocus();
            return;
        }
        SendCaptcha.setClickable(false);
        startTimer();
        SMSSDK.getInstance().getSmsCodeAsyn(mPhone, 1+"", new SmscodeListener() {
            @Override
            public void getCodeSuccess(final String uuid) {
                Toast.makeText(RegisterPage.this,"获取验证码成功，请查看手机"+uuid,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void getCodeFail(int errCode, final String errMsg) {
                stopTimer();
                Toast.makeText(RegisterPage.this,"获取验证码失败,错误代码"+errCode+",错误原因为"+errMsg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void checkChaptcha(){
        String checkPhone=phone.getText().toString();
        String Chapcha1=captcha.getText().toString();
        if(TextUtils.isEmpty(checkPhone)){
            Toast.makeText(RegisterPage.this,"请输入手机号",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Chapcha1)){
            Toast.makeText(RegisterPage.this,"请输入验证码",Toast.LENGTH_SHORT).show();
            return;
        }

        SMSSDK.getInstance().checkSmsCode(checkPhone, Chapcha1, new SmscheckListener() {
            @Override
            public void checkCodeSuccess(final String code) {
                Log.d("Register_Chapcha","Success!");
                if(registerDialog!=null&&registerDialog.isShowing()){
                    registerDialog.dismiss();
                }
//                Toast.makeText(RegisterPage.this,"验证成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void checkCodeFail(int errCode, final String errorMsg) {
                Log.d("Register_Chapcha","Fail!");
                if(registerDialog!=null&&registerDialog.isShowing()){
                    registerDialog.dismiss();
                }
                AlertDialog.Builder error_Chapcha=new AlertDialog.Builder(RegisterPage.this);
                error_Chapcha.setTitle(errCode);
                error_Chapcha.setMessage(errorMsg);
                error_Chapcha.setPositiveButton("重新注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                error_Chapcha.show();

//                Toast.makeText(RegisterPage.this,"验证失败,错误码:"+errCode+"错误信息:"+errorMsg,Toast.LENGTH_SHORT).show();

            }
        });
    }

    //用LitePal将数据存入本地数据库
    private void saveuser(){
        LitePal.getDatabase();
        List<User> queryUser= DataSupport.findAll(User.class);
        for(User user1:queryUser){
            if(user1.getUser_name().equals(mNewAccount)){
                AlertDialog.Builder errorAccount=new AlertDialog.Builder(RegisterPage.this);
                errorAccount.setTitle("错误");
                errorAccount.setMessage("该用户已注册");
                errorAccount.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NewAccout.getText().clear();
                        NewPasswd.getText().clear();
                        phone.getText().clear();
                        captcha.getText().clear();
                        NewAccout.requestFocus();
                    }
                });
                errorAccount.show();
                flag=false;
                break;
            } else if(user1.getPhone().equals(mPhone)){
                AlertDialog.Builder errorPhone=new AlertDialog.Builder(RegisterPage.this);
                errorPhone.setTitle("错误");
                errorPhone.setMessage("该手机已注册");
                errorPhone.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NewAccout.getText().clear();
                        NewPasswd.getText().clear();
                        phone.getText().clear();
                        captcha.getText().clear();
                        NewAccout.requestFocus();
                    }
                });
                errorPhone.show();
                flag=false;
                break;
            }
        }
        if(flag){
            User user=new User();
            user.setUser_name(mNewAccount);
            user.setPassword(mNewPasswd);
            user.setPhone(mPhone);
            user.save();
            AlertDialog.Builder correct=new AlertDialog.Builder(RegisterPage.this);
            correct.setTitle("成功");
            correct.setMessage("注册成功");
            correct.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            correct.show();
        }
    }

    //开始定时器
    private void startTimer(){
        remainTime=(int) (SMSSDK.getInstance().getIntervalTime()/1000);
        SendCaptcha.setText(remainTime+"s");
        if(timerTask==null){
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            remainTime--;
                            if(remainTime<=0){
                                stopTimer();
                                return;
                            }
                            SendCaptcha.setText(remainTime+"s");
                        }
                    });
                }
            };
        }
        if(timer==null){
            timer=new Timer();
        }
        timer.schedule(timerTask,1000,1000);
    }

    //停止定时器
    private void stopTimer(){
        if(timerTask!=null){
            timerTask.cancel();
            timerTask=null;
        }
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        SendCaptcha.setText("重新获取验证码");
        SendCaptcha.setClickable(true);
    }

    //将数据上传至服务器数据库
    private void sendSQL(){

    }
}
